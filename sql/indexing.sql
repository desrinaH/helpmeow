create index on contents using ivfflat (embedding vector_cosine_ops)
with
  (lists = 100);