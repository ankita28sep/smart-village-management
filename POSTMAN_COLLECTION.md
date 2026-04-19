{
  "info": {
    "_postman_id": "cfe412cb-8815-416d-bc87-3dfefe19a84c",
    "name": "smart-village",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json",
    "_exporter_id": "47050019",
    "_collection_link": "https://go.postman.co/collection/47050019-cfe412cb-8815-416d-bc87-3dfefe19a84c?source=collection_link"
  },
  "item": [
    {
      "name": "auth",
      "item": [
        {
          "name": "admin register",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"name\": \"Admin\",\r\n  \"email\": \"admin@village.com\",\r\n  \"password\": \"admin123\",\r\n  \"role\": \"ADMIN\",\r\n  \"address\": \"Office\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/auth/register",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "auth",
                "register"
              ]
            }
          },
          "response": []
        },
        {
          "name": "sarpanch register",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"name\": \"Sarpanch\",\r\n  \"email\": \"sarpanch@village.com\",\r\n  \"password\": \"sarpanch123\",\r\n  \"role\": \"SARPANCH\",\r\n  \"address\": \"Village\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/auth/register",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "auth",
                "register"
              ]
            }
          },
          "response": []
        },
        {
          "name": "citizen register",
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"name\": \"Citizen5\",\r\n  \"email\": \"citizen5@village.com\",\r\n  \"password\": \"user123\",\r\n  \"role\": \"CITIZEN\",\r\n  \"address\": \"Ward No. 5\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/auth/register",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "auth",
                "register"
              ]
            }
          },
          "response": []
        },
        {
          "name": "login",
          "event": [
            {
              "listen": "test",
              "script": {
                "exec": [
                  ""
                ],
                "type": "text/javascript",
                "packages": {},
                "requests": {}
              }
            }
          ],
          "request": {
            "method": "POST",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": "{\r\n    \"email\": \"admin@village.com\",\r\n    \"password\": \"admin123\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/auth/login",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "auth",
                "login"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "announcement",
      "item": [
        {
          "name": "post announcement",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer  eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"title\": \"Event\",\r\n  \"content\": \"all villagers are announced to attend the farmer event tomorrow\",\r\n  \"type\": \"NOTICE\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/announcements/post",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "post"
              ]
            }
          },
          "response": []
        },
        {
          "name": "Update announcement",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"title\": \"High temperature alert \",\r\n  \"content\": \"Today's temperature will be above 40 degree celcious on 26-03-2026\",\r\n  \"type\": \"ALERT\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/announcements/3",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "3"
              ]
            }
          },
          "response": []
        },
        {
          "name": "delete announcement",
          "request": {
            "method": "DELETE",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/announcements/3",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "3"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get annnouncement",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get all",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements"
              ]
            }
          },
          "response": []
        },
        {
          "name": "BY type",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements/type?type=NOTICE",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "type"
              ],
              "query": [
                {
                  "key": "type",
                  "value": "NOTICE"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "by status",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements/status?status=ACTIVE",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "status"
              ],
              "query": [
                {
                  "key": "status",
                  "value": "ACTIVE"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "active announcement",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements/active",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "active"
              ]
            }
          },
          "response": []
        },
        {
          "name": "search",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements/search?keyword=mee",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "search"
              ],
              "query": [
                {
                  "key": "keyword",
                  "value": "mee"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "recent",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/announcements/recent?since=2026-04-17T09:00:00",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "recent"
              ],
              "query": [
                {
                  "key": "since",
                  "value": "2026-04-17T09:00:00"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "update status",
          "request": {
            "method": "PATCH",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/announcements/1/status?status=INACTIVE",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "1",
                "status"
              ],
              "query": [
                {
                  "key": "status",
                  "value": "INACTIVE"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "posted by",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/announcements/posted-by/2",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "announcements",
                "posted-by",
                "2"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "Complaints",
      "item": [
        {
          "name": "raise",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"title\": \"blocked drainage\",\r\n  \"description\": \"the drainage is blocked causing floating water on road\"\r\n}\r\n",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/complaints",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints"
              ]
            }
          },
          "response": []
        },
        {
          "name": "delete",
          "request": {
            "method": "DELETE",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/2",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "2"
              ]
            }
          },
          "response": []
        },
        {
          "name": "update complaint",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWh1bEB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQwNjUxMiwiZXhwIjoxNzc2NDEwMTEyfQ.S8Lt2CCt-CEYVh5LapIKhM6H7g1YYCavLw6qrIartBo",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"title\": \"Water Supply Problem - Updated\",\r\n  \"description\": \"Water supply has not been available for 4 days now. Situation is getting worse.\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/complaints/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "assign handler",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/complaints/1/assign/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "1",
                "assign",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "update status",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/1/status?status=RESOLVED",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "1",
                "status"
              ],
              "query": [
                {
                  "key": "status",
                  "value": "RESOLVED"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "by id",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/7",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "7"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get all",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints"
              ]
            }
          },
          "response": []
        },
        {
          "name": "my complaints",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/my",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "my"
              ]
            }
          },
          "response": []
        },
        {
          "name": "by status",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/status?status=IN_PROGRESS",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "status"
              ],
              "query": [
                {
                  "key": "status",
                  "value": "IN_PROGRESS"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "by handler",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/handler/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "handler",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "recent",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQxNzQ1MCwiZXhwIjoxNzc2NDIxMDUwfQ.xLpy2J44Mdmo6cTMfQLcIB5rJHnVN-IM-_h8ktW5qEY",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/recent?since=2026-04-03",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "recent"
              ],
              "query": [
                {
                  "key": "since",
                  "value": "2026-04-03"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "search",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQxNzQ1MCwiZXhwIjoxNzc2NDIxMDUwfQ.xLpy2J44Mdmo6cTMfQLcIB5rJHnVN-IM-_h8ktW5qEY",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/complaints/search?title=wat",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "complaints",
                "search"
              ],
              "query": [
                {
                  "key": "title",
                  "value": "wat"
                }
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "scheme",
      "item": [
        {
          "name": "post",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYXJwYW5jaEB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUxODE2NCwiZXhwIjoxNzc2NTIxNzY0fQ.CZ3Riob_cFJtTFarzzheiZxoFfIs3Sx6MYhG89QQfgc",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"name\": \"Farmer Subsidy Scheme\",\r\n  \"description\": \"Provides subsidy for seeds, fertilizers, and farming equipment.\",\r\n  \"startDate\": \"2026-04-20\",\r\n  \"endDate\": \"2026-07-30\",\r\n  \"financialYear\": 2026,\r\n  \"religion\": \"ALL\",\r\n  \"casteCategory\": \"OBC\",\r\n  \"annualIncome\": 200000,\r\n  \"disability\": false,\r\n  \"department\": \"Agriculture\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/schemes",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes"
              ]
            }
          },
          "response": []
        },
        {
          "name": "update scheme",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"name\": \"Farmer Subsidy Scheme 2026 updated\",\r\n  \"description\": \"Financial assistance for farmers to purchase seeds and fertilizers.\",\r\n  \"startDate\": \"2026-04-20\",\r\n  \"endDate\": \"2026-06-30\",\r\n  \"financialYear\": 2026,\r\n  \"religion\": \"ALL\",\r\n  \"casteCategory\": \"OBC\",\r\n  \"annualIncome\": 200000,\r\n  \"disability\": false,\r\n  \"department\": \"Agriculture\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/schemes/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "deactivate scheme",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/schemes/5/deactivate",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "5",
                "deactivate"
              ]
            }
          },
          "response": []
        },
        {
          "name": "by id",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/schemes/3",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "3"
              ]
            }
          },
          "response": []
        },
        {
          "name": "active schemes",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/schemes/active",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "active"
              ]
            }
          },
          "response": []
        },
        {
          "name": "all schemes",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/schemes",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes"
              ]
            }
          },
          "response": []
        },
        {
          "name": "by posted",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzYXJwYW5jaEB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUxODE2NCwiZXhwIjoxNzc2NTIxNzY0fQ.CZ3Riob_cFJtTFarzzheiZxoFfIs3Sx6MYhG89QQfgc",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/schemes/posted-by/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "posted-by",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "search",
          "protocolProfileBehavior": {
            "disableBodyPruning": true
          },
          "request": {
            "method": "GET",
            "header": [],
            "body": {
              "mode": "raw",
              "raw": ""
            },
            "url": {
              "raw": "localhost:8080/api/schemes/search?name=far",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "search"
              ],
              "query": [
                {
                  "key": "name",
                  "value": "far"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "by eligibility",
          "request": {
            "method": "POST",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQxODkwMSwiZXhwIjoxNzc2NDIyNTAxfQ.EGuSqrSB0XwKJ6k7fi6ab2dusX44Wlh0x2PgCRQHaOQ",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n    \"department\": \"Agriculture\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/schemes/eligibility",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "schemes",
                "eligibility"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "schemeapplication",
      "item": [
        {
          "name": "apply",
          "request": {
            "auth": {
              "type": "noauth"
            },
            "method": "POST",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n\r\n  \"schemeId\": 1,\r\n  \"fullName\": \"citizen2\",\r\n  \"dob\": \"1995-09-20\",\r\n  \"address\": \"Ward No. 2\",\r\n  \"mobile\": \"9123456700\",\r\n  \"aadharNo\": \"987654321088\",\r\n  \"financialYear\": 2026,\r\n  \"annualIncome\": 120000,\r\n  \"religion\": \"MUSLIM\",\r\n  \"caste\": \"ALL\",\r\n  \"department\": \"Minority Affairs\",\r\n  \"disability\": false,\r\n  \"aadharDocPath\": \"/docs/aadhar.pdf\",\r\n  \"incomeCertPath\": \"/docs/income.pdf\",\r\n  \"bankPassbookPath\": \"/docs/bank.pdf\",\r\n  \"photoPath\": \"/docs/photo.jpg\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/applications",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications"
              ]
            }
          },
          "response": []
        },
        {
          "name": "update",
          "request": {
            "auth": {
              "type": "noauth"
            },
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJyYWh1bEB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQyMTYyMCwiZXhwIjoxNzc2NDI1MjIwfQ.Ly2hiryzgdAVYi8NGC5M3vYRm65Rx-CyddRTZQsl1ew",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n\r\n  \"schemeId\": 1,\r\n  \"fullName\": \"citizen2 updated\",\r\n  \"dob\": \"1995-09-20\",\r\n  \"address\": \"Ward No. 2\",\r\n  \"mobile\": \"9123456700\",\r\n  \"aadharNo\": \"987654321088\",\r\n  \"financialYear\": 2026,\r\n  \"annualIncome\": 120000,\r\n  \"religion\": \"MUSLIM\",\r\n  \"caste\": \"ALL\",\r\n  \"department\": \"Minority Affairs\",\r\n  \"disability\": false,\r\n  \"aadharDocPath\": \"/docs/aadhar.pdf\",\r\n  \"incomeCertPath\": \"/docs/income.pdf\",\r\n  \"bankPassbookPath\": \"/docs/bank.pdf\",\r\n  \"photoPath\": \"/docs/photo.jpg\"\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/applications/2",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "2"
              ]
            }
          },
          "response": []
        },
        {
          "name": "status",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMjEzMCwiZXhwIjoxNzc2NTI1NzMwfQ.hKxeNfywYDUTcAolGGupLrFWoKCUJakY3_7LWx1qb7c",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/1/status/APPROVED",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "1",
                "status",
                "APPROVED"
              ]
            }
          },
          "response": []
        },
        {
          "name": "delete",
          "request": {
            "method": "DELETE",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJwcml5YUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQyMjUwNywiZXhwIjoxNzc2NDI2MTA3fQ.l_LueMSp74J7_eIaJywM4Pi5f6O6F_sgxQfIi_MW0Xs",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get all",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MzU0NiwiZXhwIjoxNzc2NDk3MTQ2fQ.C2UnXnHMxdRT9JNkDBauFip7eVWGyJP8yfQR7GJy-S0",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get by id",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQyMTk4MCwiZXhwIjoxNzc2NDI1NTgwfQ.rOjQ7MJSS4Gh690MkYby-O6sbaBGyh-9EeCnYIq1Z_M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/3",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "3"
              ]
            }
          },
          "response": []
        },
        {
          "name": "my",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/my",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "my"
              ]
            }
          },
          "response": []
        },
        {
          "name": "scheme-id",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/scheme/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "scheme",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "by status",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQyMTk4MCwiZXhwIjoxNzc2NDI1NTgwfQ.rOjQ7MJSS4Gh690MkYby-O6sbaBGyh-9EeCnYIq1Z_M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/status/APPROVED",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "status",
                "APPROVED"
              ]
            }
          },
          "response": []
        },
        {
          "name": "cancel",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/applications/2/cancel",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "applications",
                "2",
                "cancel"
              ]
            }
          },
          "response": []
        }
      ]
    },
    {
      "name": "User management",
      "item": [
        {
          "name": "assign role",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQyNDAxNSwiZXhwIjoxNzc2NDI3NjE1fQ.68ZhgQElskhn-VRoAJoDP0KT_bHv6GK-rdSEdVGng2g",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/assign-role/6?role=ADMIN",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "assign-role",
                "6"
              ],
              "query": [
                {
                  "key": "role",
                  "value": "ADMIN",
                  "type": "text"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "update",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n  \"name\": \"Admin\",\r\n  \"email\": \"admin@village.com\",\r\n  \"role\": \"ADMIN\",\r\n  \"address\": \"Gram panchayat office\",\r\n  \"active\": true\r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/users/admin/update/1",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "admin",
                "update",
                "1"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get all",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users"
              ]
            }
          },
          "response": []
        },
        {
          "name": "update profile",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjaXRpemVuMUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjUyMDI5MSwiZXhwIjoxNzc2NTIzODkxfQ.gpNhl9--P-MXxbyYDbQmoSMKweTxYQO8VZKwm5dEt1M",
                "type": "text"
              }
            ],
            "body": {
              "mode": "raw",
              "raw": "{\r\n   \r\n    \"name\": \"citizen1 updated\",\r\n    \"email\": \"citizen@village.com\",\r\n    \"password\": \"citizen123\",\r\n    \"address\": \"Gram panchayat office\"\r\n  \r\n}",
              "options": {
                "raw": {
                  "language": "json"
                }
              }
            },
            "url": {
              "raw": "localhost:8080/api/users/update-profile",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "update-profile"
              ]
            }
          },
          "response": []
        },
        {
          "name": "by id",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/3",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "3"
              ]
            }
          },
          "response": []
        },
        {
          "name": "any user -admin",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/admin/2",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "admin",
                "2"
              ]
            }
          },
          "response": []
        },
        {
          "name": "by role",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/by-role?role=SARPANCH",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "by-role"
              ],
              "query": [
                {
                  "key": "role",
                  "value": "SARPANCH"
                }
              ]
            }
          },
          "response": []
        },
        {
          "name": "block",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/block/5",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "block",
                "5"
              ]
            }
          },
          "response": []
        },
        {
          "name": "activate",
          "request": {
            "method": "PUT",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/activate/5",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "activate",
                "5"
              ]
            }
          },
          "response": []
        },
        {
          "name": "get active users",
          "request": {
            "method": "GET",
            "header": [
              {
                "key": "Authorization",
                "value": "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzbmVoYUB2aWxsYWdlLmNvbSIsImlhdCI6MTc3NjQ5MDc3MiwiZXhwIjoxNzc2NDk0MzcyfQ._D66r_JRGhDuj1nQyvXB2sa62e-8LDZ8MecGMZRT_Pw",
                "type": "text"
              }
            ],
            "url": {
              "raw": "localhost:8080/api/users/active",
              "host": [
                "localhost"
              ],
              "port": "8080",
              "path": [
                "api",
                "users",
                "active"
              ]
            }
          },
          "response": []
        }
      ]
    }
  ]
}