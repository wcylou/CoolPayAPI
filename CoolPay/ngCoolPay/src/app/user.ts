export class User {

  id: string;
  username: string;
  apiKey: string;

  constructor( id: string,  username: string, apiKey: string) {
    this.id = id;
    this.username = username;
    this.apiKey = apiKey;
  }
}
