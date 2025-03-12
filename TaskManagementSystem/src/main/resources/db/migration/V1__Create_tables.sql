CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       username VARCHAR(30) NOT NULL UNIQUE,
                       password VARCHAR(80) NOT NULL,
                       email VARCHAR(50) UNIQUE
) ENGINE=InnoDB;

CREATE TABLE roles (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE users_roles (
                             user_id BIGINT NOT NULL,
                             role_id INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
) ENGINE=InnoDB;

INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

INSERT INTO users (username, password, email) VALUES
                                                  ('user', '$2a$12$7vYh0LZP6mQd8b3jKXrN4eT9cVw1sW2zC3D4E5F6G7H8I9J0K1L', 'user@gmail.com'),
                                                  ('admin', '$2a$12$7vYh0LZP6mQd8b3jKXrN4eT9cVw1sW2zC3D4E5F6G7H8I9J0K1L', 'admin@gmail.com');

INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1, 1),
                                               (2, 2);