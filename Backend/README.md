# GitHubjoacibb-Ensolvers-challange

🖥 Tech Stack
-----------------------------------------------------------------
## Backend

* Springboot 2.7.1

* Java 18

* MySQL v2.3.3

-----------------------------------------------------------------
### As a user, I want to be able to create, edit and delete notes ✓

* Create: localhost:8080/api/notes
* Edit: localhost:8080/api/notes/{idNote}
* Delete: localhost:8080/api/notes/{idNote}
  
### As a user, I want to archive/unarchive notes ✓

* Archive/Unarchive: localhost:8080/api/notes/status/{idNote}

### As a user, I want to list my active notes ✓

* List active notes: localhost:8080/api/notes/available

### As a user, I want to list my archived notes ✓

* List archived notes: localhost:8080/api/notes/archived

### As a user, I want to be able to add/remove categories to notes ✓

* Add Category: localhost:8080/api/notes/{idNote}/{Category name}
* Delete Category: localhost:8080/api/notes/{idNote}/{Category name}

### As a user, I want to be able to filter notes by category ✓

* Filter Notes by Category: localhost:8080/api/notes/categories

