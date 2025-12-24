-- 1. Должности и разряды
INSERT INTO position (id, name, grade, rate) VALUES (1, 'Механик', 3, 800);
INSERT INTO position (id, name, grade, rate) VALUES (2, 'Электрик', 4, 900);
INSERT INTO position (id, name, grade, rate) VALUES (3, 'Маляр', 4, 1000);
INSERT INTO position (id, name, grade, rate) VALUES (4, 'Механик', 5, 1200);

-- 2. Мастера
INSERT INTO master (id, full_name, position_id) VALUES (1, 'Иванов П.С.', 1);
INSERT INTO master (id, full_name, position_id) VALUES (2, 'Петров В.И.', 2);
INSERT INTO master (id, full_name, position_id) VALUES (3, 'Сидоров А.К.', 3);

-- 3. Услуги
INSERT INTO service_entity (id, name, norm_time) VALUES (1, 'Диагностика ходовой', 1.0);
INSERT INTO service_entity (id, name, norm_time) VALUES (3, 'Ремонт электропроводки', 3.0);
INSERT INTO service_entity (id, name, norm_time) VALUES (4, 'Покраска капота', 5.0);

-- 4. Автомобили
INSERT INTO car (id, brand, model, plate_number) VALUES (1, 'Toyota', 'Camry', 'A123BC');
INSERT INTO car (id, brand, model, plate_number) VALUES (2, 'Ford', 'Focus', 'B456DE');

-- 5. Клиенты
INSERT INTO client (id, full_name, phone, car_id) VALUES (101, 'Петров А.Б.', '1234567', 1);
INSERT INTO client (id, full_name, phone, car_id) VALUES (102, 'Сидоров П.К.', '7654321', 2);

-- 6. Заказы (orders)
INSERT INTO orders (id, start_date, status, client_id, master_id) VALUES (1, '2025-10-11', 'В работе', 101, 1);
INSERT INTO orders (id, start_date, status, client_id, master_id) VALUES (2, '2025-11-10', 'Завершен', 102, 2);

-- 7. Работа в заказе (WorkInOrder)
-- Для заказа 1: Диагностика (id 1) и Покраска (id 4)
INSERT INTO work_in_order (id, order_id, service_id, quantity, price_per_unit, price_date)
VALUES (1, 1, 1, 1, 5000, '2025-09-18');
INSERT INTO work_in_order (id, order_id, service_id, quantity, price_per_unit, price_date)
VALUES (2, 1, 4, 1, 15000, '2025-09-19');

-- Для заказа 2: Ремонт электропроводки (id 3)
INSERT INTO work_in_order (id, order_id, service_id, quantity, price_per_unit, price_date)
VALUES (3, 2, 3, 1, 4500, '2025-09-20');