```cmd
DELETE /my-index


POST /my-index/_doc
{
  "quantity": 10,
  "isAvailable": true,
  "lastUpdated": "2000/01/01"
}


# get mapping of the index
GET /my-index/_mapping

# dynamic mapping can lead to unexpected issues
# for example, I want to store my directory path instead of date


DELETE /my-index

# ES assumed this path will be data type.
POST /my-index/_doc
{
  "path": "2000/01/01"
}

# this insert will fails
POST /my-index/_doc
{
  "path": "02/02/2000"
}

# Get mapping for the index
GET /my-index/_mapping

# similar issues could happen for other fields as well
DELETE /my-index

# long type is assumed here
POST /my-index/_doc
{
  "phone": 1234567890
}

# this string value can be converted into long
POST /my-index/_doc
{
  "phone": "21315123592"
}



# can not be converted into long
POST /my-index/_doc
{
  "phone": "0788-049-042"
}

GET /my-index/_mapping

# we will still be seeing things in the string format and number format. Because our source document will not be touched. But behind the scenes, when it stores in the BKD tree, it will be using the number format.
GET /my-index/_search
```

