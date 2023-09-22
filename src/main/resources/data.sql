INSERT INTO categories (title, description) VALUES ('Software Development', '');
INSERT INTO categories (title, description) VALUES ('Java Spring Boot Framework', '');
INSERT INTO categories (title, description) VALUES ('Mobile App Development', '');

INSERT INTO sub_categories (title, description, cate_id) VALUES ('POS Desktop Development', '', 1);
INSERT INTO sub_categories (title, description, cate_id) VALUES ('Web Development', '', 1);
INSERT INTO sub_categories (title, description, cate_id) VALUES ('Engineering & Architecture Software', '', 1);
INSERT INTO content_sections (title, CONS_ID,COURSE_ID)
VALUES ('Engineering', 1,null);

INSERT INTO contents (title, con_id, duration, type, link,cons_id)
VALUES ('Engineering', 1, 23.0, 'Book', 'limhai@gmail.com',1);

