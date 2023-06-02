# Cloud Computing

## Usage
using local host
```
http://localhost:8080/<add endpoint here>
```
using deployed url
```
https://backend-dot-helpmeow.et.r.appspot.com/<add endpoint here>
```

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

### User Login
Request
```
POST /login
```

Body:
```json
{
    "email": "user@example.com",
    "password": "delapan8"
}
```

Response (Success):
```json
{ "email": "user@example.com", 
"your_id": "your id"}
```

Response (Error):
```json
{
"message": "Email not registered"
}
```

```json
{
"message": "Invalid password"
}
```

### User Profile
Request:
```
GET /profile/:id
```

Response (Error):
```json
{
"message": "User not found"
}
```
```json
{
"message": "Unauthorized"
}
```

Response (Success):
```json
{ 
 "email": "user@example.com", 
 "username": "user123"
 }
 ```

### User Logout
Request:
```
GET /logout/:id
```
Response (success):
```json
{
"message": "Logged Out"
}
```
Response (error):
```json
{
"message": "User not found"
}
```
```json
{
"message": "Unauthorized"
}
```
