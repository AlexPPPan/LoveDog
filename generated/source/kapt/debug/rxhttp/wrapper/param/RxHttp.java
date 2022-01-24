package rxhttp.wrapper.param;

import android.graphics.Bitmap;
import com.homework.lovedog.base.BaseUrl;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.io.IOException;
import java.lang.Boolean;
import java.lang.Byte;
import java.lang.Class;
import java.lang.Deprecated;
import java.lang.Double;
import java.lang.Float;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Short;
import java.lang.String;
import java.util.List;
import java.util.Map;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Headers.Builder;
import okhttp3.Request;
import okhttp3.Response;
import rxhttp.HttpSender;
import rxhttp.wrapper.callback.ProgressCallback;
import rxhttp.wrapper.entity.Progress;
import rxhttp.wrapper.entity.UpFile;
import rxhttp.wrapper.parse.BitmapParser;
import rxhttp.wrapper.parse.DownloadParser;
import rxhttp.wrapper.parse.ListParser;
import rxhttp.wrapper.parse.MapParser;
import rxhttp.wrapper.parse.Parser;
import rxhttp.wrapper.parse.SimpleParser;

public class RxHttp {
  private Param param;

  /**
   * The request is executed on the IO thread by default */
  private Scheduler scheduler = Schedulers.io();

  private RxHttp(Param param) {
    this.param = param;
  }

  public Param getParam() {
    return param;
  }

  public RxHttp setParam(Param param) {
    this.param = param;
    return this;
  }

  public RxHttp add(Map<? extends String, ?> map) {
    param.add(map);
    return this;
  }

  public static RxHttp with(Param param) {
    return new RxHttp(param);
  }

  public static RxHttp get(String url) {
    return with(GetParam.with(url));
  }

  public static RxHttp head(String url) {
    return with(HeadParam.with(url));
  }

  public static RxHttp postForm(String url) {
    return with(PostFormParam.with(url));
  }

  public static RxHttp postJson(String url) {
    return with(PostJsonParam.with(url));
  }

  public static RxHttp putForm(String url) {
    return with(PutFormParam.with(url));
  }

  public static RxHttp putJson(String url) {
    return with(PutJsonParam.with(url));
  }

  public static RxHttp patchForm(String url) {
    return with(PatchFormParam.with(url));
  }

  public static RxHttp patchJson(String url) {
    return with(PatchJsonParam.with(url));
  }

  public static RxHttp deleteForm(String url) {
    return with(DeleteFormParam.with(url));
  }

  public static RxHttp deleteJson(String url) {
    return with(DeleteJsonParam.with(url));
  }

  public RxHttp setUrl(String url) {
    param.setUrl(url);
    return this;
  }

  public RxHttp setJsonParams(String jsonParams) {
    param.setJsonParams(jsonParams);
    return this;
  }

  public RxHttp add(String key, Object value) {
    param.add(key,value);
    return this;
  }

  public RxHttp add(String key, Object value, boolean isAdd) {
    if(isAdd) {
      param.add(key,value);
    }
    return this;
  }

  public RxHttp setUploadMaxLength(long maxLength) {
    param.setUploadMaxLength(maxLength);
    return this;
  }

  public RxHttp add(String key, File file) {
    param.add(key,file);
    return this;
  }

  public RxHttp addFile(String key, File file) {
    param.addFile(key,file);
    return this;
  }

  public RxHttp addFile(String key, String filePath) {
    param.addFile(key,filePath);
    return this;
  }

  public RxHttp addFile(String key, String value, String filePath) {
    param.addFile(key,value,filePath);
    return this;
  }

  public RxHttp addFile(String key, String value, File file) {
    param.addFile(key,value,file);
    return this;
  }

  public RxHttp addFile(UpFile file) {
    param.addFile(file);
    return this;
  }

  public RxHttp addFile(String key, List<File> fileList) {
    param.addFile(key,fileList);
    return this;
  }

  public RxHttp addFile(List<UpFile> fileList) {
    param.addFile(fileList);
    return this;
  }

  public RxHttp removeFile(String key) {
    param.removeFile(key);
    return this;
  }

  public RxHttp addHeader(String line) {
    param.addHeader(line);
    return this;
  }

  public RxHttp addHeader(String line, boolean isAdd) {
    if(isAdd) {
      param.addHeader(line);
    }
    return this;
  }

  public RxHttp addHeader(String key, String value) {
    param.addHeader(key,value);
    return this;
  }

  public RxHttp addHeader(String key, String value, boolean isAdd) {
    if(isAdd) {
      param.addHeader(key,value);
    }
    return this;
  }

  public RxHttp setHeader(String key, String value) {
    param.setHeader(key,value);
    return this;
  }

  public RxHttp setRangeHeader(long startIndex) {
    param.setRangeHeader(startIndex);
    return this;
  }

  public RxHttp setRangeHeader(long startIndex, long endIndex) {
    param.setRangeHeader(startIndex,endIndex);
    return this;
  }

  public RxHttp removeAllHeader(String key) {
    param.removeAllHeader(key);
    return this;
  }

  public RxHttp setHeadersBuilder(Headers.Builder builder) {
    param.setHeadersBuilder(builder);
    return this;
  }

  public RxHttp setProgressCallback(ProgressCallback callback) {
    param.setProgressCallback(callback);
    return this;
  }

  public RxHttp setAssemblyEnabled(boolean enabled) {
    param.setAssemblyEnabled(enabled);
    return this;
  }

  public boolean isAssemblyEnabled() {
    return param.isAssemblyEnabled();
  }

  public String getUrl() {
    return param.getUrl();
  }

  public String getSimpleUrl() {
    return param.getSimpleUrl();
  }

  public String getHeader(String key) {
    return param.getHeader(key);
  }

  public Headers getHeaders() {
    return param.getHeaders();
  }

  public Headers.Builder getHeadersBuilder() {
    return param.getHeadersBuilder();
  }

  public Request buildRequest() {
    return param.buildRequest();
  }

  public RxHttp tag(Object tag) {
    param.tag(tag);
    return this;
  }

  public Object getTag() {
    return param.getTag();
  }

  public RxHttp cacheControl(CacheControl cacheControl) {
    param.cacheControl(cacheControl);
    return this;
  }

  public CacheControl getCacheControl() {
    return param.getCacheControl();
  }

  private static String addDomainIfAbsent(String url, String domain) {
    if (url.startsWith("http")) return url;
    String newUrl;
    if (url.startsWith("/")) {
        if (domain.endsWith("/"))
            newUrl = domain + url.substring(1);
        else
            newUrl = domain + url;
    } else if (domain.endsWith("/")) {
        newUrl = domain + url;
    } else {
        newUrl = domain + "/" + url;
    }
    return newUrl;
  }

  public Response execute() throws IOException {
    return HttpSender.execute(addDefaultDomainIfAbsent(param));
  }

  public <T> T execute(Parser<T> parser) throws IOException {
    return HttpSender.execute(addDefaultDomainIfAbsent(param),parser);
  }

  /**
   * subscribeOnXX 系列方法需要在fromXXX方法前调用，否则无效 */
  public RxHttp subscribeOn(Scheduler scheduler) {
    this.scheduler=scheduler;
    return this;
  }

  public RxHttp subscribeOnCurrent() {
    this.scheduler=null;
    return this;
  }

  public RxHttp subscribeOnIo() {
    this.scheduler=Schedulers.io();
    return this;
  }

  public RxHttp subscribeOnComputation() {
    this.scheduler=Schedulers.computation();
    return this;
  }

  public RxHttp subscribeOnNewThread() {
    this.scheduler=Schedulers.newThread();
    return this;
  }

  public RxHttp subscribeOnSingle() {
    this.scheduler=Schedulers.single();
    return this;
  }

  public RxHttp subscribeOnTrampoline() {
    this.scheduler=Schedulers.trampoline();
    return this;
  }

  public <T> Observable<T> asParser(Parser<T> parser) {
    Observable<T> observable=HttpSender.syncFrom(addDefaultDomainIfAbsent(param),parser);
    if(scheduler!=null) {
      observable=observable.subscribeOn(scheduler);
    }
    return observable;
  }

  public <T> Observable<T> asObject(Class<T> type) {
    return asParser(SimpleParser.get(type));
  }

  public <T> Observable<Bitmap> asBitmap() {
    return asParser(new BitmapParser());
  }

  public Observable<String> asString() {
    return asObject(String.class);
  }

  public Observable<Boolean> asBoolean() {
    return asObject(Boolean.class);
  }

  public Observable<Byte> asByte() {
    return asObject(Byte.class);
  }

  public Observable<Short> asShort() {
    return asObject(Short.class);
  }

  public Observable<Integer> asInteger() {
    return asObject(Integer.class);
  }

  public Observable<Long> asLong() {
    return asObject(Long.class);
  }

  public Observable<Float> asFloat() {
    return asObject(Float.class);
  }

  public Observable<Double> asDouble() {
    return asObject(Double.class);
  }

  public Observable<Map> asMap() {
    return asObject(Map.class);
  }

  public <T> Observable<Map<T, T>> asMap(Class<T> type) {
    return asParser(MapParser.get(type,type));
  }

  public <K, V> Observable<Map<K, V>> asMap(Class<K> kType, Class<V> vType) {
    return asParser(MapParser.get(kType,vType));
  }

  public <T> Observable<List<T>> asList(Class<T> type) {
    return asParser(ListParser.get(type));
  }

  public <T> Observable<String> asDownload(String destPath) {
    return asParser(new DownloadParser(destPath));
  }

  public Observable<Progress<String>> asDownloadProgress(String destPath) {
    return downloadProgress(destPath,0);
  }

  public Observable<Progress<String>> asDownloadProgress(String destPath, long offsetSize) {
    return HttpSender.downloadProgress(addDefaultDomainIfAbsent(param),destPath,offsetSize,scheduler);
  }

  public <T> Observable<Progress<String>> asUploadProgress() {
    return uploadProgress(SimpleParser.get(String.class));
  }

  public <T> Observable<Progress<T>> asUploadProgress(Parser<T> parser) {
    return HttpSender.uploadProgress(addDefaultDomainIfAbsent(param),parser,scheduler);
  }

  /**
   * @deprecated Use {@link #asParser(Parser)} */
  @Deprecated
  public <T> Observable<T> from(Parser<T> parser) {
    Observable<T> observable=HttpSender.syncFrom(addDefaultDomainIfAbsent(param),parser);
    if(scheduler!=null) {
      observable=observable.subscribeOn(scheduler);
    }
    return observable;
  }

  /**
   * @deprecated Use {@link #asString()} */
  @Deprecated
  public Observable<String> from() {
    return fromSimpleParser(String.class);
  }

  /**
   * @deprecated Use {@link #asBoolean()} */
  @Deprecated
  public Observable<Boolean> fromBoolean() {
    return fromSimpleParser(Boolean.class);
  }

  /**
   * @deprecated Use {@link #asByte()} */
  @Deprecated
  public Observable<Byte> fromByte() {
    return fromSimpleParser(Byte.class);
  }

  /**
   * @deprecated Use {@link #asShort()} */
  @Deprecated
  public Observable<Short> fromShort() {
    return fromSimpleParser(Short.class);
  }

  /**
   * @deprecated Use {@link #asInteger()} */
  @Deprecated
  public Observable<Integer> fromInteger() {
    return fromSimpleParser(Integer.class);
  }

  /**
   * @deprecated Use {@link #asLong()} */
  @Deprecated
  public Observable<Long> fromLong() {
    return fromSimpleParser(Long.class);
  }

  /**
   * @deprecated Use {@link #asFloat()} */
  @Deprecated
  public Observable<Float> fromFloat() {
    return fromSimpleParser(Float.class);
  }

  /**
   * @deprecated Use {@link #asDouble()} */
  @Deprecated
  public Observable<Double> fromDouble() {
    return fromSimpleParser(Double.class);
  }

  /**
   * @deprecated Use {@link #asObject(Class)} */
  @Deprecated
  public <T> Observable<T> fromSimpleParser(Class<T> type) {
    return from(SimpleParser.get(type));
  }

  /**
   * @deprecated Use {@link #asList(Class)} */
  @Deprecated
  public <T> Observable<List<T>> fromListParser(Class<T> type) {
    return from(ListParser.get(type));
  }

  /**
   * @deprecated Reference {@link #subscribeOnCurrent()} */
  @Deprecated
  public <T> Observable<T> syncFrom(Parser<T> parser) {
    return HttpSender.syncFrom(addDefaultDomainIfAbsent(param),parser);
  }

  /**
   * @deprecated Use {@link #asDownload(String)} */
  @Deprecated
  public <T> Observable<String> download(String destPath) {
    return from(new DownloadParser(destPath));
  }

  /**
   * @deprecated Use {@link #asDownloadProgress(String)} */
  @Deprecated
  public Observable<Progress<String>> downloadProgress(String destPath) {
    return downloadProgress(destPath,0);
  }

  /**
   * @deprecated Use {@link #asDownloadProgress(String,long)} */
  @Deprecated
  public Observable<Progress<String>> downloadProgress(String destPath, long offsetSize) {
    return HttpSender.downloadProgress(addDefaultDomainIfAbsent(param),destPath,offsetSize,scheduler);
  }

  /**
   * @deprecated Use {@link #asUploadProgress()} */
  @Deprecated
  public <T> Observable<Progress<String>> uploadProgress() {
    return uploadProgress(SimpleParser.get(String.class));
  }

  /**
   * @deprecated Use {@link #asUploadProgress(Parser)} */
  @Deprecated
  public <T> Observable<Progress<T>> uploadProgress(Parser<T> parser) {
    return HttpSender.uploadProgress(addDefaultDomainIfAbsent(param),parser,scheduler);
  }

  public Param addDefaultDomainIfAbsent(Param param) {
    String newUrl = addDomainIfAbsent(param.getSimpleUrl(), BaseUrl.PLATFORM_URL);
    param.setUrl(newUrl);
    return param;
  }
}
