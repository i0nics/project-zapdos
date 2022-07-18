# Technical Debt

## CI Tools

### Gradle
We used gradle 


### Auto Changelog Generation
Creating and maintaining the changelog is often a very time-consuming process. We have managed to automate this by using the node package manger to install some required tools. We utilized husky, commit-lint, and config-conventional to ensure that all developers of this project adhere to the Angular commit convention. Next, we utilize the standard-version tool that automatically goes through our commits and updates our changelog with a single command as described below.

```
# npm run script
npm run release -- --release-as minor
# Or
npm run release -- --release-as 1.1.0
```
