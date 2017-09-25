# tableadd

Add strings in a postgres database and every time you push the "Add" button the content of a json file is inserted as well.

## Usage
Project can be deployed on Heroku so no need to install local postgres. 
Heroku has a command line interface which can be used to query the database for content. Pick the installer suitable for your OS from https://devcenter.heroku.com/articles/heroku-cli.
After installing Heroku CLI type "heroku pg:psql" to acess the interactive terminal. Once you're in, use "SELECT * FROM items;" to see what's in the table. 

## License

MIT License (https://opensource.org/licenses/MIT)
