# Small Performance Regression Testing Sample

To go along with the "How JRebel for Android does
performance testing" talk given at Mobilization 2016.

## Slides

[Here.](https://speakerdeck.com/madisp/how-jrebel-for-android-does-performance-testing)

## Setting up Elasticsearch & Logstash & Kibana

I used the following versions (download from [here](https://www.elastic.co/downloads/kibana) and unzip/untar):

* Elasticsearch 2.4.1
* Logstash 2.4.0
* Kibana 4.6.1

## Starting elasticsearch

```
$ unzip elasticsearch-2.4.1.zip
$ bin/elasticsearch
```

## Starting logstash

```
$ unzip logstash-2.4.0.zip

# update the hard-coded path to logstash template:
$ vim $PATH_TO_REPO/config/logstash.conf

$ bin/logstash --config $PATH_TO_REPO/config/logstash.conf
```

## Starting kibana

```
$ tar zxvf kibana-4.6.1-darwin-x86_64.tar.gz
$ bin/kibana --config $PATH_TO_REPO/config/kibana.yml
```

## General Instructions

Everything up and running? You should see Kibana running at [http://localhost:5601](http://localhost:5601).

Try to send an event manually with:

```
$ curl -XPUT 'http://127.0.0.1:1138' \
       -H "content-type: application/json" \
       -d '{
             "version": "1.2.3",
             "test": "helloWorld",
             "timeMillis": 123
           }'
```

After refreshing the indicies in Kibana the event should
show up under the _Discover_ tab.

Try playing around with the `TestThreadSleep` unit test
or the `measureDebugStartup` Gradle task.
