//package mase.ericsson.sentry;
//import java.lang.Exception;
//import io.sentry.Sentry;
//public class Sentry {
//
//	public static void main(String[] args) {
//
//Sentry.init(options -> {
//  options.setDsn("https://fe47f6c382c8401b96dbb5e87b39b282@o1153133.ingest.sentry.io/6232201");
//});
//		try {
//			  throw new Exception("This is a test.");
//			} catch (Exception e) {
//			  Sentry.captureException(e);
//			}
//	}
//
//
//}
