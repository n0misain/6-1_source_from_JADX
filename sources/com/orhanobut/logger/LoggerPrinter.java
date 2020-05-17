package com.orhanobut.logger;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LoggerPrinter implements Printer {
    private static final int JSON_INDENT = 2;
    private final ThreadLocal<String> localTag = new ThreadLocal();
    private final List<LogAdapter> logAdapters = new ArrayList();

    LoggerPrinter() {
    }

    /* renamed from: t */
    public Printer mo4193t(String tag) {
        if (tag != null) {
            this.localTag.set(tag);
        }
        return this;
    }

    /* renamed from: d */
    public void mo4187d(String message, Object... args) {
        log(3, null, message, args);
    }

    /* renamed from: d */
    public void mo4186d(Object object) {
        log(3, null, Utils.toString(object), new Object[0]);
    }

    /* renamed from: e */
    public void mo4188e(String message, Object... args) {
        mo4189e(null, message, args);
    }

    /* renamed from: e */
    public void mo4189e(Throwable throwable, String message, Object... args) {
        log(6, throwable, message, args);
    }

    /* renamed from: w */
    public void mo4195w(String message, Object... args) {
        log(5, null, message, args);
    }

    /* renamed from: i */
    public void mo4190i(String message, Object... args) {
        log(4, null, message, args);
    }

    /* renamed from: v */
    public void mo4194v(String message, Object... args) {
        log(2, null, message, args);
    }

    public void wtf(String message, Object... args) {
        log(7, null, message, args);
    }

    public void json(String json) {
        if (Utils.isEmpty(json)) {
            mo4186d("Empty/Null json content");
            return;
        }
        try {
            json = json.trim();
            if (json.startsWith("{")) {
                mo4186d(new JSONObject(json).toString(2));
            } else if (json.startsWith("[")) {
                mo4186d(new JSONArray(json).toString(2));
            } else {
                mo4188e("Invalid Json", new Object[0]);
            }
        } catch (JSONException e) {
            mo4188e("Invalid Json", new Object[0]);
        }
    }

    public void xml(String xml) {
        if (Utils.isEmpty(xml)) {
            mo4186d("Empty/Null xml content");
            return;
        }
        try {
            Source xmlInput = new StreamSource(new StringReader(xml));
            StreamResult xmlOutput = new StreamResult(new StringWriter());
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty("indent", "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(xmlInput, xmlOutput);
            mo4186d(xmlOutput.getWriter().toString().replaceFirst(">", ">\n"));
        } catch (TransformerException e) {
            mo4188e("Invalid xml", new Object[0]);
        }
    }

    public synchronized void log(int priority, String tag, String message, Throwable throwable) {
        if (!(throwable == null || message == null)) {
            message = message + " : " + Utils.getStackTraceString(throwable);
        }
        if (throwable != null && message == null) {
            message = Utils.getStackTraceString(throwable);
        }
        if (Utils.isEmpty(message)) {
            message = "Empty/NULL log message";
        }
        for (LogAdapter adapter : this.logAdapters) {
            if (adapter.isLoggable(priority, tag)) {
                adapter.log(priority, tag, message);
            }
        }
    }

    public void clearLogAdapters() {
        this.logAdapters.clear();
    }

    public void addAdapter(LogAdapter adapter) {
        this.logAdapters.add(adapter);
    }

    private synchronized void log(int priority, Throwable throwable, String msg, Object... args) {
        log(priority, getTag(), createMessage(msg, args), throwable);
    }

    private String getTag() {
        String tag = (String) this.localTag.get();
        if (tag == null) {
            return null;
        }
        this.localTag.remove();
        return tag;
    }

    private String createMessage(String message, Object... args) {
        return (args == null || args.length == 0) ? message : String.format(message, args);
    }
}
