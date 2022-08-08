package raido.apisvc.util;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateUtil {
  public static ZoneId SYD_ZONE_ID = ZoneId.of("Australia/Sydney");
  public static ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
  
  public static final String ISO_MINUTES_FORMAT = "yyyy-MM-dd HH:mm";
  public static final String ISO_SECONDS_FORMAT = "yyyy-MM-dd HH:mm:ss";
  
  // doco says it's thread safe
  public static final DateTimeFormatter ISO_DATE_TIME = 
    DateTimeFormatter.ISO_DATE_TIME;
  public static final DateTimeFormatter ISO_SECONDS_DATE_TIME = 
    DateTimeFormatter.ofPattern(ISO_SECONDS_FORMAT);
  
  public static TimeZone utcTimezone() {
    return TimeZone.getTimeZone("UTC");
  }

  public static String formatIsoDateTime(LocalDateTime d){
    return ISO_DATE_TIME.format(d);
  }

  public static String formatUtcDateTime(
    String format,
    @Nullable LocalDateTime d,
    String defaultValue
  ) {
    if( d == null ){
      return defaultValue;
    }

    return formatUtcDateTime(format, d);
  }
  
  public static String formatUtcDateTime(
    String format,
    LocalDateTime d
  ) {
    return formatDateTime(format, UTC_ZONE_ID, d);
  }

  /**
   The given date is interpreted as if it is in UTC timezone, then formatted 
   as if it were in `targetZone` timezone.
   This is done to remove the dependency on System.default() timezone, this 
   method returns the same value regardless of what the JVM is set to.
   */
  public static String formatDateTime(
    String format,
    ZoneId targetZone,
    LocalDateTime d
  ) {
    Guard.notNull(d);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return formatter.format(d.atZone(UTC_ZONE_ID).
      withZoneSameInstant(targetZone));
  }

  /**
   The given date is parsed as if it were in `targetZone`, then returned to 
   the caller after being converted to UTC timezone.
   This is done to remove the dependency on System.default() timezone, this 
   method returns the same value regardless of what the JVM is set to.
   */
  public static LocalDateTime parseDateTime(
    String format,
    ZoneId targetZone,
    @Nullable String value
  ){
    if( value == null ){
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);  
    ZonedDateTime zdt = LocalDateTime.parse(value, formatter).atZone(targetZone);
    
    /* systemDefault() should be UTC on EC2 machines, in containers, or run
    from gradle; but if you just invoke the JVM directly on a dev machine,  
    it'll be your local TZ */
    return zdt.withZoneSameInstant(UTC_ZONE_ID).toLocalDateTime();
  }
  
  /**
   Given date will be formatted to "DynamoDB S3 export" format, using
   Sydney as the targetZone.
   */
  public static String formatDynamoDateTime(LocalDateTime d){
    return DateUtil.formatDateTime(ISO_SECONDS_FORMAT, SYD_ZONE_ID, d);
  }

  /**
   Given date will be parsed from "DynamoDB S3 export" format, using
   Sydney as the targetZone.
   */
  public static LocalDateTime parseDynamoDateTime(String value){
    return DateUtil.parseDateTime(ISO_SECONDS_FORMAT, SYD_ZONE_ID, value);
  }

}
