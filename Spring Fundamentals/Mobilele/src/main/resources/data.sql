INSERT INTO users (id, is_active, username, first_name, last_name, password)
VALUES
    (2, 1, 'user', 'User', 'Userov', '36daf93e320e8161a5f20a6d49bfbefaaf5723584c28482ff25aaf405f604573fb7314d9ec413c794439da0dc5b5dbea');


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
