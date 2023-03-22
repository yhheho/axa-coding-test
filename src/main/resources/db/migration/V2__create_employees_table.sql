CREATE TABLE employees (
      id INTEGER PRIMARY KEY AUTO_INCREMENT,
      name VARCHAR(255) NOT NULL,
      salary INTEGER,
      department VARCHAR(255) NOT NULL,
      created_at TIMESTAMP NOT NULL,
      updated_at TIMESTAMP NOT NULL
);

INSERT INTO employees (name, salary, department, created_at, updated_at) VALUES ('John', 9000000, 'backend', NOW(), NOW());
INSERT INTO employees (name, salary, department, created_at, updated_at) VALUES ('Jack', 8000000, 'backend', NOW(), NOW());
INSERT INTO employees (name, salary, department, created_at, updated_at) VALUES ('Chris', 7000000, 'human resource', NOW(), NOW());