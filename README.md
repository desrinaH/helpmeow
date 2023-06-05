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

### Create Content :

```
POST /:id/create
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


### Get All Content :

```
GET /home
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
  ]
}
```

Response (error):
```json
{
    "error": "Invalid request all data"
}
```

### Search by Role :

```
POST /search/role
```

Body:
```json
{
	"role":"gifter"
}
```

Response (Success):
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
	  "role":"gifter",
	  "upload_by_username":"user123",
	  "created_at":"2023-06-01T09:44:20.585+00:00"
	}
	  ]
}
```

Response (error):
```json
{
	"error":"Get data failed"
}
```



### Search by Gender :

```
POST /search/gender
```

Body:
```json
{
	"role":"male"
}
```

Response (Success):
```json

{
    "data": [
        {
            "id": 21,
            "photo": "https://storage.googleapis.com/img-helpmeow/contents/Screenshot_2023-05-16-00-30-01-762_com.frontrow.vlog.jpg",
            "name": "tom",
            "gender": "male",
            "breed": "balinese",
            "location": "antartica",
            "description": "suka nabrak orang dan mencuri",
            "upload_by_username": "hamayan",
            "created_at": "2023-06-02T16:31:28.466+00:00",
            "role": "seeker",
            "longitude": null,
            "latitude": null,
            "embedding": null
        },
        {
            "id": 22,
            "photo": "https://storage.googleapis.com/img-helpmeow/contents/1685758505528_Screenshot_2023-05-16-00-30-01-762_com.frontrow.vlog.jpg",
            "name": "jerry",
            "gender": "male",
            "breed": "itsnese",
            "location": "its",
            "description": "suka nabrak orang dan mencuri",
            "upload_by_username": "hamayan",
            "created_at": "2023-06-03T02:15:06.054+00:00",
            "role": "seeker",
            "longitude": -7.288698,
            "latitude": 112.789976,
            "embedding": null
        }
    ]
}
```

Response (error):
```json
{
	"error":"Get data failed"
}
```

### Get Information for Information Page :

```
POST /information
```

Body:
```json
{
	"breed":"Burmlla"
}
```

Response (Success):
```json
{
    "data": {
        "fur_color": "Hitam, Biru, Coklat, Ungu, Karamel, Beige",
        "treatment": "sedang",
        "character": "Manja, Suka perhatian, Penyayang, Aktif, Adaptif",
        "cost": "Rp 7.000.000 - Rp 16.000.000",
        "eye_color": "green",
        "fur_pattern": "Tortie, Shade",
        "weight": "3 kg - 6 kg",
        "details": "Burmilla adalah salah satu ras kucing yang terbentuk dari persilangan antara kucing burma dengan chinchilla. Kucing jenis ini berukuran sedang, serupa dengan kucing Burma. Struktur tulangnya kokoh, badannya berotot dan punggungnya lurus. Kucing berjenis kelamin betina jauh lebih kecil dan lebih menarik ketimbang kucing yang jantan. Jenis kepalanya agak membulat, dengan ujung yang cenderung lancip, dan telinganya lebar. Warna mata kucing ini kuning keemasan bahkan mungkin juga berwarna hijau. Lapisan rambut luarnya pendek dan mengikuti bentuk tubuh. Lapisan rambut luar yang cenderung pucat dengan ujung berwarna gelap di sekujur tubuhnya. Dahinya memiliki fitur yang dilihat sepintas mirip dengan bentuk huruf M.",
        "picture": "https://storage.googleapis.com/kagglesdsdata/datasets/3311702/5827782/Burmilla/Burmilla_110.jpg?X-Goog-Algorithm=GOOG4-RSA-SHA256&X-Goog-Credential=databundle-worker-v2%40kaggle-161607.iam.gserviceaccount.com%2F20230604%2Fauto%2Fstorage%2Fgoog4_request&X-Goog-Date=20230604T090346Z&X-Goog-Expires=345600&X-Goog-SignedHeaders=host&X-Goog-Signature=9fc08daeb382cbf4a20c2366cdab025f24938aa15574a20cf8323dee6914d03b930e68c15af06833f19a13d79d38a44322f3b91f27f5792739cf8c9db8ad6fad77befaff97799fc58db4ca8abcfa5d83fec335a4e5861c3099f9bb1ac2a617322b720baa88c0ac0f0fb622824fd56e982bda9e80979b4c77e86f716e45149e566cf095ebbebc2e2d567dec9f0834890e9b06af956532030dc635d7097aa88763e735d5778a144449de8af448189f7a045b7f3659972a90ec9b2541f5ab63300d804f36951ce3fdf9f223b9eec4ad980b948a8da1ead23ba09d20f80d8e06035811a48515419d9a540bd073f65f15e8f0bc6ee542fcb65d6fd0807234cf8f6441",
        "breed": "Burmilla",
        "age": "12 - 15 Tahun"
    }
}


```

Response (error):
```json
{
	"error":"Get data failed"
}
```
