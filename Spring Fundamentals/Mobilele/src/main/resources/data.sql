INSERT INTO users (id, is_active, username, first_name, last_name, password)
VALUES
    (1, 1, 'admin', 'Admin', 'Adminov', '12345'),
    (2, 1, 'user', 'User', 'Userov', '12345');


INSERT INTO roles (`id`, `roles`)
VALUES
    (1, 'ADMIN'),
    (2, 'USER');

INSERT INTO `brands` (`id`, `name`)
VALUES
    (1, 'Toyota'),
    (2, 'Ford');

INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES
    (1, 'CAR', 1, 'Camry'),
    (2, 'CAR', 1, 'Corolla'),
    (3, 'CAR', 2, 'Focus'),
    (4, 'CAR', 2, 'Fiesta');
