Reference: http://elastic.co/guide/en/elasticsearch/reference/current/analysis-tokenizers.html

```cmd
# standard - divides text into terms on word boundaries & it removes most punctuation symbols.
# mostly splits when it encouters any non-alphanumeric char (with some exceptions)
POST /_analyze
{
  "text": "This is a simple text to see how tokens are generated.",
  "tokenizer": "standard"
}

# when you have a dot which is surrounded by alphanumeric character (12.34, omain.com). The standard tokenizer will not split
POST /_analyze
{
  "text": "Reach out to <b>Support</b> at support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance!",
  "tokenizer": "standard"
}

POST /_analyze
{
  "text": "Reach out to <b>Support</b> at support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance!",
  "char_filter": [
    {
      "type": "html_strip"
    }
    ], 
  "tokenizer": "standard"
}

# standard + keep urls and emails as they are
POST /_analyze
{
  "text": "Reach out to <b>Support</b> at support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance!",
  "tokenizer": "uax_url_email"
}

# devides text into terms whenever it encounters any whitespace character
POST /_analyze
{
  "text": "Reach out to Support at                 support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance !",
  "tokenizer": "whitespace"
}


# the letter tokenizer breaks text into terms whenever it encounters a character which is not a letter.
POST /_analyze
{
    "text": "Reach out to Support at                 support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance !",
  "tokenizer": "letter"
}

# keyword - do not anything - some case you just want to character filters or token filters, but tokenizer is required
POST  /_analyze
{
  "text": "Reach out to Support at support@domain.com or send email to 123, Non Main Street, Atlanta, for assistance !",
  "tokenizer": "keyword"
}
```