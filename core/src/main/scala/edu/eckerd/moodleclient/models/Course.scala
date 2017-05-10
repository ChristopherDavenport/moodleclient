package edu.eckerd.moodleclient.models

//import io.circe.generic.JsonCodec
import io.circe.generic.auto._

case class Course(
                 id: Int,
                 shortname: String,
                 categoryid: Int,
                 categorysortorder: Int,
                 fullname: String,
                 displayname: String,
                 idnumber: String,
                 summary: String,
                 summaryformat: Int,
                 format: String,
                 showgrades: Int,
                 newsitems: Int,
                 startdate: Int,
                 enddate: Int,
                 numsections: Int,
                 maxbytes: Int,
                 showreports: Int,
                 visible: Int,
                 groupmode: Int,
                 groupmodeforce: Int,
                 defaultgroupingid: Int,
                 timecreated: Int,
                 timemodified: Int,
                 enablecompletion: Int,
                 completionnotify: Int,
                 lang: String,
                 forcetheme: String,
                 courseformatoptions: List[KeyValue]
                 )




