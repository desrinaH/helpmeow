# Cloud Computing - HelpMeow
## Endpoints 
### Register User
Request: 
```
POST /register
``` 
Body:
```json
{
    "username": "user321",
    "email": "user@example.co.id",
    "password": "password8",
    "confirmPass": "password8"
}
```
Success response:
```json
{
  "message": "Account created successfully"
}
```
Error response:
```json
{
  "message": "Email already exists"
}
{
  "message": "Password must be at least 8 characters long"
}
{
  "message": "Please make sure the password and confirmation match"
}
```
### Login User
Request: 
```
POST /login 
``` 
Body:
```json
{
    "email": "user@example.co.id",
    "password": "password8"
}
```
Success response:
```json
{
  "message": "Logged in",
  "your_token": "your_generated_token",
  "your_id": "user_id"
}
```
Error response:
```json
{
  "message": "Email not registered"
}
{
  "message": "Invalid password"
}
```
### Profile 
Request: 
```
GET /profile/:id 
``` 
Headers:
```
Authorization: Bearer your_token
```
Success response:
```json
{
  "email": "user@example.co.id",
  "username": "user321",
  "your_id": "user_id",
  "your_token": "your_generated_token"
}
```
Error response:
```json
{
  "message": "Unauthorized"
}
```

### Logout
Request: 
```
GET /logout/:id 
``` 
Headers:
```
Authorization: Bearer your_token
```
Success response:
```json
{
  "message": "Logged out"
}
```
Error response:
```json
{
  "message": "Unauthorized"
}
```

