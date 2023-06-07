create or replace function match_documents (
  query_embedding vector(1536),
  match_threshold float,
  match_count int
)
returns table (
  id bigint,
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
  similarity float
)
language sql stable
as $$
    select
        contents.id,
        contents.photo,
        contents.name,
        contents.gender,
        contents.breed,
        contents.location,
        contents.description,
        contents.upload_by_username,
        contents.created_at,
        contents.role,
        contents.longitude,
        contents.latitude,
        1 - (contents.embedding <=> query_embedding) as similarity
    from contents
    where 1 - (contents.embedding <=> query_embedding) > match_threshold
    order by similarity desc
    limit match_count;
$$;
