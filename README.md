# TestbedRegistry

## Development

Before you can build this project, you must install and configure the following dependencies on your machine:
1. [Node.js][]: We use Node to run a development web server and build the project.
   Depending on your system, you can install Node either from source or as a pre-packaged bundle.

After installing Node, you should be able to run the following command to install development tools (like
[Bower][] and [BrowserSync][]). You will only need to run this command when dependencies change in package.json.

    npm install

We use [Gulp][] as our build system. Install the Gulp command-line tool globally with:

    npm install -g gulp-cli

Run the following commands in two separate terminals to create a blissful development experience where your browser
auto-refreshes when files change on your hard drive.

    ./mvnw
    gulp

Bower is used to manage CSS and JavaScript dependencies used in this application. You can upgrade dependencies by
specifying a newer version in `bower.json`. You can also run `bower update` and `bower install` to manage dependencies.
Add the `-h` flag on any command to see how you can use it. For example, `bower update -h`.

For further instructions on how to develop with FiestaIoT Registry UI, have a look at [Using FiestaIoT Registry UI in development][].


##Create UI Testbed Registry SQL database

Before you run the SQL script, modify username, password and database name in case you'd like to.
$ mysql -u root -p < database_structure.sql


## Building for production





To build dev 

   ./mvnw -DskipTests=true -Pdev clean package


To optimize the TestbedRegistry application for production, run:

  

This will concatenate and minify the client CSS and JavaScript files. It will also modify `index.html` so it references these new files.
To ensure everything worked, run:

    java -jar target/*.war

Then navigate to [http://localhost:8080](http://localhost:8080) in your browser.


## Testing

To launch your application's tests, run:

    ./mvnw clean test

### Client tests

Unit tests are run by [Karma][] and written with [Jasmine][]. They're located in `src/test/javascript/` and can be run with:

    gulp test



For more information, refer to the [Running tests page][].

## Using Docker to simplify development (optional)

You can use Docker to improve your FiestaIoT Registry UI development experience. A number of docker-compose configuration are available in the `src/main/docker` folder to launch required third party services.
For example, to start a mysql database in a docker container, run:

    docker-compose -f src/main/docker/mysql.yml up -d

To stop it and remove the container, run:

    docker-compose -f src/main/docker/mysql.yml down

You can also fully dockerize your application and all the services that it depends on.
To achieve this, first build a docker image of your app by running:

    ./mvnw package -Pprod docker:build

Then run:

    docker-compose -f src/main/docker/app.yml up -d


## Deploy to Widfly Fiesta IoT TEST ENV

    1. Before you run the SQL script, modify username, password and database name in case you'd like to.
        $ mysql -u fiesta-iot -p F135t@pass < database_structure.sql
    2. To optimize the TestbedRegistry application for production, run:

        ./mvnw -DskipTests=true  -Pdev clean package

    3. Upload target/ui.testbed-registry.war  to Widfly   


## Deploy to Widfly Fiesta IoT PROD ENV

    1. Before you run the SQL script, modify username, password and database name in case you'd like to.
        $ mysql -u fiesta -p fiesta < database_structure.sql
    2. To optimize the TestbedRegistry application for production, run:

        ./mvnw -DskipTests=true -Pprod clean package

    3. Upload target/ui.testbed-registry.war  to Widfly   