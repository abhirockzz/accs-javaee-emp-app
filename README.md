## Build

- `git clone https://github.com/abhirockzz/accs-javaee-emp-app.git`
- `mvn clean install`

The build process will create `employee.war` in the `target` directory

## Run locally

You can use any Java EE 7 container

- Create `jdbc/dbcs` data source
- Deploy `employee.war` 

## Deploy to Oracle Application Container Cloud

- Install [PSM CLI](https://docs.oracle.com/en/cloud/paas/java-cloud/pscli/using-command-line-interface-1.html)
- modify the `deployment.json` to fill in the details (name, username, password) corresponding to your **Oracle Database cloud service** instance
- execute `psm accs push -n employEEs -r javaee -s hourly -d deployment.json -p target/employee.war`

## For details

- check out the blog - [Running Java EE 7 apps on Oracle Application Container Cloud]()