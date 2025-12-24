-- 1. КЛИЕНТЫ (Уникальность по полю phone)
INSERT INTO clients (full_name, phone)
VALUES ('Иван Иванов', '79001112233')
ON CONFLICT (phone) DO NOTHING;

INSERT INTO clients (full_name, phone)
VALUES ('Петр Петров', '79995556677')
ON CONFLICT (phone) DO NOTHING;


-- 2. УСЛУГИ (Уникальность по полю title)
INSERT INTO service_catalog (title, price)
VALUES ('Замена масла', 1500.0)
ON CONFLICT (title) DO NOTHING;

INSERT INTO service_catalog (title, price)
VALUES ('Диагностика', 2000.0)
ON CONFLICT (title) DO NOTHING;


-- 3. АВТОМОБИЛИ (Уникальность по полю plate_number)
-- Важно: мы ищем владельца по телефону, чтобы не зависеть от меняющихся ID
INSERT INTO vehicles (model, plate_number, owner_id)
SELECT 'Toyota Camry', 'А123ВС77', id FROM clients WHERE phone = '79001112233'
ON CONFLICT (plate_number) DO NOTHING;

INSERT INTO vehicles (model, plate_number, owner_id)
SELECT 'Lada Vesta', 'К555РР99', id FROM clients WHERE phone = '79995556677'
ON CONFLICT (plate_number) DO NOTHING;


-- 4. ЗАКАЗЫ (Уникальность по сочетанию: Дата + Авто + Услуга)
INSERT INTO service_order (order_date, status, service_id, vehicle_id)
SELECT '2025-12-23', 'Принято', s.id, v.id
FROM service_catalog s, vehicles v
WHERE s.title = 'Замена масла' AND v.plate_number = 'А123ВС77'
ON CONFLICT (order_date, vehicle_id, service_id) DO NOTHING;

INSERT INTO service_order (order_date, status, service_id, vehicle_id)
SELECT '2025-12-23', 'В работе', s.id, v.id
FROM service_catalog s, vehicles v
WHERE s.title = 'Диагностика' AND v.plate_number = 'К555РР99'
ON CONFLICT (order_date, vehicle_id, service_id) DO NOTHING;