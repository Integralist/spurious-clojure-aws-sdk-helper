# Spurious Clojure AWS SDK Helper

The original [Spurious Ruby AWS SDK Helper](https://github.com/stevenjack/spurious-ruby-awssdk-helper)
worked by loading the helper code only when in "dev mode"; followed by loading 
a dev YAML file which changed some ENV variables and then calling a `configure` method on the helper.

In Clojure we have the following requirement: the application needs to switch the location of where it loads config from. We have:

- Environment variables (using [clj-dotenv](https://github.com/rentpath/clj-dotenv)):
  - `AWS_REGION: eu-west-1`
  - `AWS_ACCESS_KEY_ID: development_access`
  - `AWS_SECRET_ACCESS_KEY: development_secret`
- JSON config (one for `development` and one for `production`):
  - `"sequencer_table_name": "council_sequencer"`
  - `"lookup_table_name": "council_lookup"`
  - `"s3_object_path": "news-council-shared/development/html"`
  - `"s3_bucket_id": "news-council-shared"`

> The application will default to loading `.env.dev` and `development/config.json`

## Usage

```bash
$ java -jar spurious-aws-sdk-helper-0.1.0-standalone.jar [args]
```

## Testing locally

```bash
lein pom
lein jar
lein install
```

## License

Copyright © 2015 Integralist

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
