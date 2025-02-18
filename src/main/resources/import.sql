-- Autores
INSERT INTO authors (nombre, apellido) VALUES ("John", "Doe");
INSERT INTO authors (nombre, apellido) VALUES("Jane", "Smith");
INSERT INTO authors (nombre, apellido) VALUES("Michael", "Johnson");
INSERT INTO authors (nombre, apellido) VALUES("Emily", "Davis");
INSERT INTO authors (nombre, apellido) VALUES("James", "Brown");
INSERT INTO authors (nombre, apellido) VALUES("Mary", "Wilson");
INSERT INTO authors (nombre, apellido) VALUES("David", "Moore");
INSERT INTO authors (nombre, apellido) VALUES("Linda", "Taylor");
INSERT INTO authors (nombre, apellido) VALUES("Robert", "Anderson");
INSERT INTO authors (nombre, apellido) VALUES("Patricia", "Thomas");

-- Libros de John Doe
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-789-0", "Java Programming Basics", 29.99, 1);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-790-6", "Advanced Java Concepts", 39.99, 1);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-791-3", "Java Design Patterns", 49.99, 1);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-792-0", "Java for Beginners", 19.99, 1);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-793-7", "Mastering Java", 59.99, 1);

-- Libros de Jane Smith
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-794-4", "Python for Beginners", 19.99, 2);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-795-1", "Advanced Python", 39.99, 2);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-796-8", "Data Science with Python", 49.99, 2);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-797-5", "Machine Learning with Python", 59.99, 2);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-798-2", "Python Data Structures", 29.99, 2);

-- Libros de Michael Johnson
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-799-9", "C++ for Beginners", 24.99, 3);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-800-2", "Mastering C++", 44.99, 3);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-801-9", "C++ Algorithms and Data Structures", 54.99, 3);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-802-6", "Advanced C++ Programming", 64.99, 3);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-803-3", "C++ Design Patterns", 34.99, 3);

-- Libros de Emily Davis
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-804-0", "Web Development with HTML", 19.99, 4);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-805-7", "CSS for Web Design", 29.99, 4);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-806-4", "JavaScript for Web Developers", 39.99, 4);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-807-1", "Building Responsive Websites", 49.99, 4);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-808-8", "Web Design Principles", 59.99, 4);

-- Libros de James Brown
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-809-5", "HTML and CSS Basics", 19.99, 5);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-810-1", "Advanced HTML5", 39.99, 5);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-811-8", "Responsive Web Design with CSS", 29.99, 5);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-812-5", "JavaScript and DOM Manipulation", 49.99, 5);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-813-2", "Frontend Frameworks Overview", 59.99, 5);

-- Libros de Mary Wilson
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-814-9", "Introduction to Databases", 19.99, 6);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-815-6", "SQL for Beginners", 29.99, 6);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-816-3", "Advanced SQL Queries", 39.99, 6);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-817-0", "Database Design Principles", 49.99, 6);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-818-7", "NoSQL Databases", 59.99, 6);

-- Libros de David Moore
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-819-4", "Beginning Java Programming", 24.99, 7);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-820-0", "Java Advanced Topics", 44.99, 7);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-821-7", "Java Performance Tuning", 54.99, 7);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-822-4", "Spring Framework Basics", 64.99, 7);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-823-1", "Microservices with Java", 34.99, 7);

-- Libros de Linda Taylor
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-824-8", "Introduction to Machine Learning", 29.99, 8);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-825-5", "Python for Machine Learning", 39.99, 8);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-826-2", "Deep Learning with TensorFlow", 49.99, 8);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-827-9", "Neural Networks Explained", 59.99, 8);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-828-6", "Machine Learning Algorithms", 69.99, 8);

-- Libros de Robert Anderson
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-829-3", "JavaScript for Beginners", 19.99, 9);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-830-9", "Advanced JavaScript", 39.99, 9);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-831-6", "React for Beginners", 29.99, 9);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-832-3", "Node.js for Developers", 49.99, 9);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-833-0", "Vue.js Basics", 59.99, 9);

-- Libros de Patricia Thomas
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-834-7", "Introduction to Algorithms", 24.99, 10);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES  ("978-1-23456-835-4", "Data Structures and Algorithms", 34.99, 10);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-836-1", "Algorithm Design Techniques", 44.99, 10);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES ("978-1-23456-837-8", "Advanced Algorithms", 54.99, 10);
INSERT INTO books (isbn, titulo, precio, author_id) VALUES("978-1-23456-838-5", "Greedy Algorithms", 64.99, 10);
