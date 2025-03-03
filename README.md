# ELASTICSEARCH

![img.png](img.png)

![img_1.png](img_1.png)

The status is yellow because Elasticsearch warns us that we are running a single node cluster.
If this node goes down, there is no way to handle requests like search request etc.

## Inverted index

Elasticsearch uses Apache Lucene behind the scenes. In fact, Lucene is the one that handles the storage, indexing
documents, search and retrieving the documents, etc. Apache Lucene is a Java library, it's not a server. It's designed
to be added as a dependency.

Elasticsearch is a distributed REST server on top of Apache Lucene.

The actual core for the search engine is Lucene, but making it highly available, horizontally scalable and making it as
a proper search engine with the appropriate APIs for any application to use it. That's what Elasticsearch does.

When we add a document to the index, Elasticsearch will handle it over to Lucene. Lucene will take the document, and it
will parse the document means it will tokenize the value.

![img_2.png](img_2.png)

It does not modify the document, but it will extract the information as tokens.
With these tokens, it will be maintaining one table called inverted index.

* Inverted Index

| Term    | Document IDs | Positions |
|---------|--------------|-----------|
| Apple   | 1,3          | [0],[0]   |
| iPhone  | 1            | [1]       |
| 14      | 1            | [2]       |
| Samsung | 2            | [0]       |
| Galaxy  | 2            | [1]       |
| S21     | 2            | [2]       |
| MacBook | 3            | [1]       |
| Pro     | 3            | [2]       |

When we search "Apple", it will return all the documents that have "Apple" in it based on the inverted index,
particularly the document IDs.

In RDBMS, it will have to scan the entire table.

### Search (Simplified)

* Elasticsearch:
    * Similar to Java HashMap → O(1)
* RDBMS:
    * Similar to the List contains method → O(n)

When search "Apple Iphone", the Elasticsearch will tokenize the input strike like this:

* ["Apple", "Iphone"]

We will not only be tokenizing the document when we store, but also we will be tokenizing this input.

In the real world, we might be having multiple fields like name, description, etc. In that case for every field we will
be having one inverted index like this:

```json
[
  {
    "name": "Apple Iphone",
    "description": "phone with stuff"
  },
  {
    "name": "Samsung Galaxy",
    "description": "sleek phone with stuff"
  }
]
```

we have one inverted index for name:

| Term    | Document IDs | Positions |
|---------|--------------|-----------|
| Apple   | 1,3          | [0],[0]   |
| iPhone  | 1            | [1]       |
| 14      | 1            | [2]       |
| Samsung | 2            | [0]       |
| Galaxy  | 2            | [1]       |
| S21     | 2            | [2]       |
| MacBook | 3            | [1]       |
| Pro     | 3            | [2]       |

we have one inverted index for description:

| Term  | Document IDs | Positions |
|-------|--------------|-----------|
| phone | 1, 2         | [0],[1]   |
| stuff | 1, 2         | [2],[3]   |
| sleek | 2            | [0]       |

* Tokenization is just one part of the analysis process.

### Term Dictionary, term frequency, Document frequency

![img_3.png](img_3.png)

### Segment

* Fundamental unit of storage and indexing

* When we store some documents into our product index. Behind the scenes, it's split into multiple segments. Each
  segment is a self-contained, immutable index.


* The Product index has three segments:
    * products-0
    * products-1
    * products-2

Each of every segment will act like a mini index. They will be having their own inverted index.

#### Adding Documents

* New documents would be in the memory buffer.
* Periodically (time/memory), it is flushed to disk as a new Segment!
* We will have multiple segments.
    * Props: Superfast writes.
    * Cons: Search might be slow as we have to search across multiple segments to retrieve the results.

#### Segments Merge

* Smaller Segments are periodically merged into Larger Segments
    * Larger Segments help with search
    * Efficient Parallel search across segments.

#### Immutable Segment!

* Deletion is NOT immediately reflected in the segment. Instead, there will be a marker.
* When we delete a document, it will be adding one entry saying that the product ID is deleted.
* When a request comes in, the segment which has been marked will also return the result with the product ID deleted.
* However, there will be a deletion marker saying that this document is deleted.
* So because of that, the Lucene will remove that, or it will not include the result returned by this segment.
* Later as part of the merge process, it will be removed completely.

* Update => Delete + Insert.

## Refresh API

```cmd
POST /{index}/_refresh
```

Any new documents inserted or updated will not reflect immediately during search, or it will not be immediately
available for search. By default, Elasticsearch refreshes after every 1 second, so we can search new documents.

When we insert a bunch of documents one by one, Es will try to index documents in bulk.
We have to use refresh API to get documents in Spring Boot. Because the program will be executed very fast more than one
second.