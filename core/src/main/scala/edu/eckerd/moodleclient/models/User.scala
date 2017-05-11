package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec

@JsonCodec case class User(
                            username: String ,
                            firstname: String,
                            lastname: String,
                            email: String,

                            auth : String = "Manual", //Auth plugins include manual, ldap, imap, etc
                            id: Option[Int] = None,
                            fullname: Option[String] = None,
                            idnumber : Option[String] = None, //An arbitrary ID code number perhaps from the institution

                            address: Option[String] = None, //Postal address
                            phone1: Option[String] = None, //Phone 1
                            phone2: Option[String] = None, //Phone 2

                            icq : Option[String] = None, //icq number
                            skype : Option[String] = None, //skype id
                            yahoo : Option[String] = None, //yahoo id
                            aim : Option[String] = None,  //aim id
                            msn:  Option[String] = None,  //msn number

                            department : Option[String] = None, //department
                            institution : Option[String] = None, //institution

                            interests : Option[String] = None, //user interests (separated by commas)

                            firstaccess : Option[Int] = None, //first access to the site (0 if never)
                            lastaccess : Option[Int] = None, //last access to the site (0 if never)
                            suspended : Option[Boolean] = None, //Suspend user account, either false to enable user login or true to disable it
                            confirmed : Option[Boolean] = None, //Active user: 1 if confirmed, 0 otherwise

                            lang : Option[String] = None , //Language code such as "en", must exist on server
                            calendartype : Option[String] = None, //Calendar type such as "gregorian", must exist on server

                            theme : Option[String] = None, //Theme name such as "standard", must exist on server

                            timezone : String = "99", //Timezone code such as Australia/Perth, or 99 for default

                            mailformat : Option[Int] = None, //Mail format code is 0 for plain text, 1 for HTML etc
                            description : Option[String] = None, //User profile description
                            descriptionformat : Option[Int] = None, //int format (1 = HTML, 0 = MOODLE, 2 = PLAIN or 4 = MARKDOWN)

                            city : Option[String] = None, //Home city of the user
                            country : Option[String] = None, //Home country code of the user, such as AU or CZ

                            url : Option[String] = None, //URL of the user
                            profileimageurlsmall: Option[String] = None,   //User image profile URL - small version
                            profileimageurl : Option[String] = None,   //User image profile URL - big version

                            customfields: Option[List[UserCustomField]] = None, //User custom fields (also known as user profile fields)
                            preferences: Option[List[UserPreference]] = None
                          )
