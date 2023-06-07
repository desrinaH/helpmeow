create table contents (
  id bigint primary key,
  photo varchar,
  name varchar,
  gender varchar,
  breed varchar,
  location varchar,
  description text,
  upload_by_username varchar,
  created_at timestamp,
  role varchar,
  longitude float8,
  latitude float8,
  embedding vector,
  embedding vector(1536)
);