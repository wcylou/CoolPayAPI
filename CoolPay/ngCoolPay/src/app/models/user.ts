export class User {

  id: string;
  username: string;
  apikey: string;

  constructor( id?: string,  username?: string, apikey?: string) {
    this.id = id;
    this.username = username;
    this.apikey = apikey;
  }
}
