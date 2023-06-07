CREATE OR REPLACE FUNCTION search_posts(keyword text)
  RETURNS SETOF contents
AS
$func$
  SELECT *
  FROM contents
  WHERE to_tsvector(name || ' ' || breed || ' ' || location || ' ' || description)
    @@ to_tsquery(keyword);
$func$
LANGUAGE SQL;
