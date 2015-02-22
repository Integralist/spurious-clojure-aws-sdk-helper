# Spurious Clojure AWS SDK Helper

Inspired by the original [Spurious Ruby AWS SDK Helper](https://github.com/spurious-io/ruby-awssdk-helper)

You can find this library on [Clojars](http://clojars.org/spurious-aws-sdk-helper)

## Usage

You can use this helper library from within either a standard Clojure application or via an application running within a [Docker](https://www.docker.com/) container.

The process is effectively the same; the only difference is that you'll need to pass a `:docker` keyword to the `configure` function instead of a `:app` keyword (see below for examples).

### Step 1

Add the following dependency to your `profile.clj`:

```clj
{:dependencies [[spurious-aws-sdk-helper "0.1.0"]]}
```

Internally we use the [Amazonica](https://github.com/mcohen01/amazonica/) AWS library. 

You'll see we also use it in the following example repository: 
https://github.com/Integralist/spurious-clojure-example/ 

> Note: your project doesn't *have* to use Amazonica; 
> it just made creating our example project easier.

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
