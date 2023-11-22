INSERT INTO roles (`id`, `roles`)
VALUES (1, 'ADMIN'),
       (2, 'USER');


INSERT INTO users (id, is_active, username, first_name, last_name, password, role_id)
VALUES (2, 1, 'user', 'User', 'Userov',
        '36daf93e320e8161a5f20a6d49bfbefaaf5723584c28482ff25aaf405f604573fb7314d9ec413c794439da0dc5b5dbea', 2),
       (1, 1, 'admin', 'Admin', 'Adminov',
        '36daf93e320e8161a5f20a6d49bfbefaaf5723584c28482ff25aaf405f604573fb7314d9ec413c794439da0dc5b5dbea', 1);


INSERT INTO `brands` (`id`, `name`)
VALUES (1, 'Toyota'),
       (2, 'Ford'),
       (3, 'Honda');



INSERT INTO `models` (`id`, `category`, `brand_id`, `name`)
VALUES (1, 'CAR', 1, 'Camry'),
       (2, 'CAR', 1, 'Corolla'),
       (3, 'CAR', 2, 'Focus'),
       (4, 'CAR', 2, 'Fiesta'),
       (5, 'CAR', 3, 'Civic');

INSERT INTO `offers` (id, created, description, engine, image_url, mileage, price, transmission, year, model_id)
VALUES (1, '2023-11-22 20:23:12.209579', 'Honda Civic 6 gen', 'GASOLINE',
        'https://en.wikipedia.org/wiki/Honda_Civic_%28sixth_generation%29#/media/File:EK9_Honda_Civic_Type_R_IMG_7754_(2).jpg',
        305500, 10500, 'MANUAL', 1998, 5),
       (2, '2023-11-22 20:23:12.209579', 'Honda Civic 6 gen', 'GASOLINE',
        'https://www.hondacivicadatok.hu/img/slide2.jpg',
        305500, 7000, 'MANUAL', 1998, 5),
       (3, '2023-11-22 20:23:12.209579', 'Honda Civic 6 gen', 'GASOLINE',
        'https://www.auto-data.net/images/f70/Honda-Civic-VI-Hatchback.jpg',
        305500, 5000, 'MANUAL', 1998, 5),
       (4, '2023-11-22 20:23:12.209579', 'Honda Civic 6 gen', 'GASOLINE',
        'https://cdn2.focus.bg/mobile/photosorg/282/1/big/11573546754256282_Ve.jpg',
        305500, 3500, 'MANUAL', 1998, 5),
       (5, '2023-11-22 20:23:12.209579', 'Honda Civic 6 gen', 'GASOLINE',
        'https://s.car.info/image_files/1920/honda-civic-3-door-side-vallakratraffen-2022-1-1210054.jpg',
        305500, 15000, 'MANUAL', 1998, 5);