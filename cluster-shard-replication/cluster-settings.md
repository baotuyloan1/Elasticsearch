```cmd
# check if the index is present
GET /_cat/indices/my-index?v

# try tp insert without creating an index
# it will automatically create an index, if the index doesn't exit., this is default settings.
POST /my-index/_doc
{
    "name": ":baond"
}

/*
    cluster settings, by default it not include the default values
        persistent - permanent (recommended)
        transient - temporary 
*/
GET /_cluster/settings

# include defaults values, you will see this property : auto_create_index : true
GET /_cluster/settings?include_defaults

# we can disable the auto index creation
PUT /_cluster/settings
{
    "persistent": {
        "action.auto_create_index": false
    }
}

# try to insert without creating an index
POST /my-index1/_doc
{
    "name": "vinoth"
}


```