INSERT INTO orders (order_id, customer_name, address, summ)
VALUES
(101, '�.�.������', '���������, 14', 14780.70),
(102, '�.�.������', '�������, 21', 1780.50),
(103, 'Michael Chumaher', 'German', 9500),
(104, 'John Smith', 'Oklahoma city', 55000);

INSERT INTO order_details (item_number, serial_number, product_name, qty, order_id)
VALUES
(1, 'vfd4354dsf', '����', 7, 101),
(2, 'ver412�f', '���� ������', 3, 101),
(3, 'vfz45435d', '���� �������', 2, 101),
(4, 'vb4354ad', '�����', 1, 101),
(1, 'vfd4354dsf', '�������', 7, 102),
(2, 'ver412�f', '������', 3, 102),
(1, 'd4354dsf', 'Butter', 17, 103),
(2, 'ver412�f', 'Soar cream', 24, 103),
(3, 'z45435d', 'Brush', 12, 103),
(1, 'fdd43gdf', 'Butter', 17, 104),
(2, 'ver41dfg�f', 'Soar cream', 24, 104),
(3, 'vfz4sdf', 'Brush', 12, 104),
(4, 'vdsf412�f', 'Soar cream', 24, 104),
(5, 'fz45435d', 'Brush', 12, 104);


