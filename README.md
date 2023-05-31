# Cloud Computing

## Endpoints
### User Register
Request
```
POST /register
```
Body:
```json
{
  "email": "user@example.com",
  "username": "user123",
  "password": "password123",
  "confirmPass": "password123"
}
```

Response (Success):

```json
{
  "message": "Account created successfully"
}
```

Response (Error):

```json
{
  "message": "Invalid email format"
}
```


POST /login
```json
{
    "email": "user@example.com",
    "password": "delapan8"
}
```

Response (Success):
```json
{ "email": "user@example.com", 
"your_id": "userDoc.id"}
```

Response (Error):
```json
{
"message": "'Email not registered'"
}
```

```json
{
"message": "'Invalid password'"
}
```

