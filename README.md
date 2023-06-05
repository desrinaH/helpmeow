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
### Content Controller

Creat Content :
```
Body:
```json
{
  "photo": "[user@example.com](https://storage.googleapis.com/img-       helpmeow/contents/1685758505528_Screenshot_2023-05-16-00-30-01-762_com.frontrow.vlog.jpg)",
  "name":"Tom"
  "gender": "male",
  "breed": "scottish",
  "longitude": "000000000"
  "latitude": "000000000",
  "location": "jakarta",
  "description": "he is very cute",
  "role": "gifter"
  
}
```
Response (Success):

```json
{
"data": {
    "message": "Content created successfully" }
}
```
Response (error):
```json
{
    "error": "Invalid request body"
}
```

Get All Content :
```
GET /homePage
```

Response (success):
```json
{
  "data": [
    {
      "id": "content_id_1",
      "photo": "https://storage.googleapis.com/img-helpmeow/contents/1685758505528_Screenshot_2023-05-16-00-30-01-762_com.frontrow.vlog.jpg",
      "name": "Tom",
      "gender": "male",
      "breed": "scottish",
      "longitude": "000000000",
      "latitude": "000000000",
      "location": "jakarta",
      "description": "he is very cute",
      "role": "gifter"
    },
    {
      "id": "content_id_2",
      "photo": "https://storage.googleapis.com/img-helpmeow/contents/1685758505528_Screenshot_2023-05-16-00-30-01-762_com.frontrow.vlog.jpg",
      "name": "Jerry",
      "gender": "female",
      "breed": "persian",
      "longitude": "111111111",
      "latitude": "111111111",
      "location": "bandung",
      "description": "she is adorable",
      "role": "adopter"
    },
    ...
  ]
}
```



