package edu.eckerd.moodleclient.models

import io.circe.generic.JsonCodec



@JsonCodec case class Course(

                 shortname: String,
                 categoryid: Option[Int],

                 fullname: String,
                 displayname: Option[String],

                 summary: Option[String] = None, // Summary
                 startdate: Option[Int] = None, // Timestamp when the Course Start
                 enddate: Option[Int] = None, // Time When The Course End

                 id: Option[Int] = None,
                 idnumber: Option[String] = None,

                 categorysortorder: Option[Int] = None,
                 summaryformat: Option[Int] = None, // Summary format (1= HTML, 0=Moodle, 2 = Plain, 4=Markdown)

                 format: Option[String] = None, // weeks, topics, social, site, ..
                 showgrades: Option[Int] = None, // 1 if grades are shown otherwise, 0
                 newsitems: Option[Int] =  None, // Number of Recent Items Appearing on the Course Page

                 numsections: Option[Int] = None, // (deprecated, use courseformatoptions) number of weeks/topics

                 maxbytes: Option[Int] = Some(0), // largest size of file that can be uploaded into the course
                 showreports: Option[Int] = Some(0), //

                 visible: Option[Int] = Some(1), //1: available to Studen, 0: not available

                 groupmode: Option[Int] = Some(0), // no group, separate, visible
                 groupmodeforce: Option[Int] = Some(0), //1: yes, 0:no
                 defaultgroupingid: Option[Int] = Some(0), // default grouping id

                 timecreated: Option[Int] = None,
                 timemodified: Option[Int] = None,
                 enablecompletion: Option[Int] = None, //
                 completionnotify: Option[Int] = None, // 1: yes , 0: no
                 lang: Option[String] = None, // Forced Course Language
                 forcetheme: Option[String] = None, // Name of Force Them
                 courseformatoptions: Option[List[CourseFormat]] = None // Additional Options for Particular Course Format
                 )




