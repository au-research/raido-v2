package raido.util;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class DateUtil {
  public static ZoneId UTC_ZONE_ID = ZoneId.of("UTC");
  public static final String SHORT_DATETIME_FORMAT = "yyyy-MM-dd HH:mm";
  
  public static TimeZone utcTimezone() {
    return TimeZone.getTimeZone("UTC");
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
    Guard.notNull(d);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
    return formatter.format(d.atZone(UTC_ZONE_ID).
      withZoneSameInstant(UTC_ZONE_ID));
  }

  static LocalDateTime parseDateTime(
    String format,
    ZoneId zone,
    @Nullable String value
  ){
    if( value == null ){
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);  
    ZonedDateTime zdt = LocalDateTime.parse(value, formatter).atZone(zone);
    
    /* systemDefault() should be UTC on EC2 machines, in containers, or run
    from gradle; but if you just invoke the JVM directly on a dev machine,  
    it'll be your local TZ */
    return zdt.withZoneSameInstant(zone).toLocalDateTime();
  }
  
  static LocalDateTime parseUtcDateTime(
    String format,
    @Nullable String value
  ){
    if( value == null ){
      return null;
    }

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);  
    ZonedDateTime zdt = LocalDateTime.parse(value, formatter).
      atZone(UTC_ZONE_ID);
    
    /* systemDefault() should be UTC on EC2 machines, in containers, or run
    from gradle; but if you just invoke the JVM directly on a dev machine,  
    it'll be your local TZ */
    return zdt.withZoneSameInstant(UTC_ZONE_ID).toLocalDateTime();
  }

  public static String formatUtcShortDateTime(LocalDateTime d){
    return formatUtcDateTime(SHORT_DATETIME_FORMAT, d);
  }


}
