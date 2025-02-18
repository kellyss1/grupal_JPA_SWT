package programacion.repositorio.impl;

import programacion.db.Book;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class MaxPriceBookTask extends RecursiveTask<Book> {
    private static final int THRESHOLD = 20;
    private List<Book> books;
    private int start;
    private int end;

    public MaxPriceBookTask(List<Book> books, int start, int end) {
        this.books = books;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Book compute() {
        if (end - start <= THRESHOLD) {
            return findMaxPrecioBook();
        } else {
            int mid = (start + end) / 2;
            MaxPriceBookTask leftTask = new MaxPriceBookTask(books, start, mid);
            MaxPriceBookTask rightTask = new MaxPriceBookTask(books, mid, end);
            leftTask.fork();
            Book rightResult = rightTask.compute();
            Book leftResult = leftTask.join();
            return leftResult.getPrecio() > rightResult.getPrecio() ? leftResult : rightResult;
        }
    }

    private Book findMaxPrecioBook() {
        Book maxPriceBook = books.get(start);
        for (int i = start + 1; i < end; i++) {
            if (books.get(i).getPrecio() > maxPriceBook.getPrecio()) {
                maxPriceBook = books.get(i);
            }
        }
        return maxPriceBook;
    }
}