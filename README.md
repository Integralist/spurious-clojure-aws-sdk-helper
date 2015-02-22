# Spurious Clojure AWS SDK Helper

Inspired by the original [Spurious Ruby AWS SDK Helper](https://github.com/spurious-io/ruby-awssdk-helper): which configures the AWS SDK to use [Spurious](https://github.com/spurious-io/spurious) (Spurious is a toolset allowing development against a subset of AWS resources, locally).

## Usage

You can use this helper library from within either a standard Clojure application or via an application running within a [Docker](https://www.docker.com/) container ([here is an example application](https://github.com/integralist/spurious-clojure-example)).

The process is effectively the same; the only difference is that you'll need to pass a `:docker` keyword to the `configure` function instead of a `:app` keyword (see below for examples).

### Step 1

Add the following dependency to your `profile.clj`:

```clj
{:dependencies [[spurious-aws-sdk-helper "0.1.0"]]}
```

The link to the library on Clojars is [http://clojars.org/spurious-aws-sdk-helper](http://clojars.org/spurious-aws-sdk-helper).

Internally the library uses the [Amazonica](https://github.com/mcohen01/amazonica/) library, as there is currently no official Clojure AWS SDK.

You'll see we also use Amazonica in the following example application repository: 
https://github.com/Integralist/spurious-clojure-example/ 

> Note: your project doesn't *have* to use Amazonica; 
> it just made creating the example application quicker/easier.

### Step 2

Somewhere in your code add:

```clj
(require '[spurious-aws-sdk-helper.core :as core])

(core/configure :app {:s3  "test-bucket"
                      :sqs "test-queue"
                      :ddb (slurp "./resources/config/schema.yaml")})))
```

It's worth mentioning that you don't have to specify all the different services. You can pick and choose which one's you pass to the `configure` function to create for you.

> Note: we load a DynamoDB schema from a `.yaml` file 
> But feel free to inline the schema or load it from some other source

If your application is running within a Docker container then the `configure` function would be called like so:

```clj
(core/configure :docker {:s3  "test-bucket"
                         :sqs "test-queue"
                         :ddb (slurp "./resources/config/schema.yaml")})))
```

If you already have a part of your application that creates AWS resources, then you'll not need the Spurious helper to create those resources; but you will want to make sure that you call the Spurious helper's `core/configure` function *before* that part of your application is executed. This will allow the helper to still configure settings that allow the AWS SDK to work with the Spurious services.

The `core/configure` function has multi-arity and so you can call it without the map data structure, like so:

```clj
(core/configure :app)
```

> Note: the Java SDK, which Spurious utilises under the covers, requires that each SDK API function is passed the same auth credentials (access/secret keys and endpoint). This means your application will need to ensure it uses the right credentials (the example app linked to above shows that you can use `[spurious-aws-sdk-helper.utils :refer [endpoint cred]]` for dev mode)

## Testing locally

Clone this repository, make changes and then run:

```bash
lein install
```

> Note: this will install the library into your local `~/.m2` cache directory. 
> Leiningen will look for dependencies in the cache first

Once installed, you can safely reference the changes within this library in your application

## License

Copyright © 2015 Integralist

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
