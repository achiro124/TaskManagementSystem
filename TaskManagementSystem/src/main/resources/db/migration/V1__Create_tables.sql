-- Создание таблиц
CREATE TABLE users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       password VARCHAR(80) NOT NULL,
                       email VARCHAR(50) UNIQUE
) ENGINE=InnoDB;

CREATE TABLE roles (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE statuses (
                          id INT PRIMARY KEY AUTO_INCREMENT,
                          name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE priorities (
                            id INT PRIMARY KEY AUTO_INCREMENT,
                            name VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

CREATE TABLE todo (
                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                      title VARCHAR(255) NOT NULL,
                      description TEXT,
                      completed BOOLEAN DEFAULT FALSE,
                      status_id INT NOT NULL,
                      priority_id INT NOT NULL,
                      author_id BIGINT NOT NULL,
                      assignee_id BIGINT,
                      FOREIGN KEY (status_id) REFERENCES statuses(id) ON DELETE CASCADE,
                      FOREIGN KEY (priority_id) REFERENCES priorities(id) ON DELETE CASCADE,
                      FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE,
                      FOREIGN KEY (assignee_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE comments (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          text TEXT NOT NULL,
                          created_at DATETIME NOT NULL,
                          todo_id BIGINT NOT NULL,
                          author_id BIGINT NOT NULL,
                          FOREIGN KEY (todo_id) REFERENCES todo(id) ON DELETE CASCADE,
                          FOREIGN KEY (author_id) REFERENCES users(id) ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE TABLE users_roles (
                             user_id BIGINT NOT NULL,
                             role_id INT NOT NULL,
                             PRIMARY KEY (user_id, role_id),
                             FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                             FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
) ENGINE=InnoDB;

-- Вставка начальных данных
INSERT INTO roles (name) VALUES
                             ('ROLE_USER'),
                             ('ROLE_ADMIN');

INSERT INTO priorities (name) VALUES
                                  ('HIGH'),
                                  ('MEDIUM'),
                                  ('LOWER');

INSERT INTO statuses (name) VALUES
                                ('IN_WAITING'),
                                ('IN_PROGRESS'),
                                ('COMPLETED');

-- Вставка пользователей
INSERT INTO users (password, email) VALUES
                                        ('$2a$12$/qJD408d4fbaBkcZSXAUjuAOX7dbsC.CU454cJjxaOgPnVsbgxDTm', 'user@gmail.com'),
                                        ('$2a$12$/qJD408d4fbaBkcZSXAUjuAOX7dbsC.CU454cJjxaOgPnVsbgxDTm', 'admin@gmail.com');

-- Вставка задач
INSERT INTO todo (title, description, completed, status_id, priority_id, author_id, assignee_id)
VALUES
    ('Задача 1', 'Описание задачи 1', FALSE, 1, 1, 1, 2),
    ('Задача 2', 'Описание задачи 2', FALSE, 2, 2, 2, 1);

-- Вставка комментариев (после вставки задач)
INSERT INTO comments (text, created_at, todo_id, author_id)
VALUES
    ('Первый комментарий к задаче', NOW(), 1, 1),
    ('Второй комментарий к задаче', NOW(), 1, 1),
    ('Третий комментарий к задаче', NOW(), 1, 1);

-- Вставка ролей пользователей
INSERT INTO users_roles (user_id, role_id) VALUES
                                               (1, 1),
                                               (2, 2);