# JuniorWorkersAPI

## About

Restfull API provider for my [Junior Workers](https://github.com/JohnDelta/junior-workers) project

The previous API was written is PHP and I transered it to Java using Maven to setup the file structure.

# Build Info

### Maven project | dynamic web module 4

## Requirements
- JDK 1.8+
- Maven 3.8+

## dependencies
- jaxrs Jersey
- org.json
- MySQL connector java
- jjwt

## Setup the MySQL Database (DB name: junior_workers, User: juniorworkers_user, Password: juniorworkers_password)
- Localhost mysql server is set to localhost:3306. Change it from com.junior_workers.database_controllers.Database.java if you use a different host
- Login to MySQL command line
- Find the folder: JuniorWorkersAPI/DatabaseScript/script.sql and run it to your MySQL command line to create the database
- Create a new controller account for the new database

` create user 'juniorworkers_user'@'%' identified by 'juniorworkers_password'; `

- Give user the necessary privileges

` grant all on junior_workers.* to 'juniorworkers_user'@'%'; `

## Setup uploads folder
- Create a folder on server (outside of the project) for file uploading and setup the folder's path in the MediaService.java file final var with name UPLOADS_PATH. 
- Default path is: ` C:\\Users\\john\\Documents\\junior_workers_uploads\\ `

## Install API (setup DB first required)
- clone the project to a location and open it

` git clone https://github.com/JohnDelta/JuniorWorkersAPI.git `

` cd JuniorWorkersAPI `

- build and run the project locally on your local server
	
# API Requests (local testing server: localhost:8080/api)

## login
- url: localhost:8080/api/user/login
- method: POST
- content-type: application/json
- body: ` {username: "", password: ""} `
- returns: application/json

## account create
- url: localhost:8080/api/user/create
- method: POST
- content-type: application/json
- body:

```
 	{
		"email": "", 
		"password": "",
		"firstname": "",
		"lastname": "",
		"role": "" ("candidate" or "hirer")
	}
```
 
- returns: application/json

## update account (role: candidate)
- *All object keys must be presented. If they're empty or null or negative they won't update any values
- url: localhost:8080/api/user/update
- method: POST
- content-type: application/json
- body:
 
```  
	{
		"jwt": "jwt key"
		"user": {
			"firstname": "",
			"lastname": "",
			"availability": "",
			"title": "",
			"bio": "",
			"image_path": "",
			"video_path": "",
			"resume_path": ""
		},
		"skill": [
			{
				"title": "",
				"id_skill": ""
			},
		],
		"language": [
			{
				"title": "",
				"id_language": "",
				"language_level": {
					"id_language_level": "",
					"title": ""
				}
			},
		],
		"education": [
			{
				"title": "",
				"id_education": "",
				"education_level": {
					"id_education_level": "",
					"title": ""
				}
			},
		],
		"experience": [
			{
				"id_experience": ""
				"company": "",
				"date": "",
				"profession": {
					"id_profession": "",
					"title": ""
				}
			},
		]
	}
	
```
- returns: application/json

## update account (role: hirer)
- *All object keys must be presented. If they're empty or null or negative they won't update any values
- url: localhost:8080/api/user/update
- method: POST
- content-type: application/json
- body:
 
```  
	{
		"jwt": "jwt key"
		"user": {
			"firstname": "",
			"lastname": "",
			"availability": "",
			"title": "",
			"bio": "",
		},
		"job_post": [
			{
				"description": "",
				"title": "",
				"profession": {
					"id_profession": "",
					"title": ""
				}
			},
		]
	}
	
```
- returns: application/json

## delete account (jwt owner)
- url: localhost:8080/api/user/delete
- method: POST
- content-type: application/json
- body: ` {"jwt":"jwt key"} `
- returns: application/json

## get account
- url: localhost:8080/api/user/get
- method: POST
- content-type: application/json
- body: ` {"jwt":"jwt key", "email": "user's email"} `
- returns: application/json
` All user's data including education, language, experience, job_post, skill `

## get all models
- url: localhost:8080/api/model/get/all
- method: GET
- returns: application/json

```

	"education": [
		{
			"id_education": "",
			"title": ""
		},
	],
	"education_level": [
		{
			"id_education_level": "",
			"title": ""
		},
	],
	"language": [
		{
			"id_language": "",
			"title": ""
		},
	],
	"language_level": [
		{
			"id_language_level": "",
			"title": ""
		},
	],
	"skill": [
		{
			"id_skillI": "",
			"title": ""
		},
	],
	"profession": [
		{
			"id_skill": "",
			"title": ""
		},
	]

```
## search
- url: localhost:8080/api/search/all?role={search-type}&key={search-input}
- method: POST
- content-type: application/json
- body: ` {"role": "candidate | hirer", "key":"any key to search for"} `
- returns: application/json

```
[
	{
		<both for hirers and candidates>
		"email": "string",
		"firstname": "string",
		"lastname": "string",
		"title": "string",
		"role": "string",
		"image_path": "string",
		
		<only for hirers else null>
		"job_title": "string",
		"description": "string",
		"id_profession": "integer",
	}
]

```

## get user's image
- url: localhost:8080/api/media/images/get/{image_path}
- method: POST
- content-type: path param
- returns: image/png

## update user's image
- url: localhost:8080/api/media/images/update
- method: POST
- content-type: multipart/form-data
- body: ` (formData) {"jwt": "token", "file": image file, "file_type": "png"} `
- returns: application/json

## reset user's image
- url: localhost:8080/api/media/images/reset
- method: POST
- content-type: application/json
- body: ` {"jwt": "token"} `
- returns: application/json

## get user's video
- url: localhost:8080/api/media/video/get/{video_path}
- method: POST
- content-type: path param
- returns: application_octet_stream

## update user's video
- url: localhost:8080/api/media/video/update
- method: POST
- content-type: multipart/form-data
- body: ` (formData) {"jwt": "token", "file": video file, "file_type": "mp4"} `
- returns: application/json










