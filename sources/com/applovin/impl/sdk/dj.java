package com.applovin.impl.sdk;

import com.applovin.impl.p000a.C0466q;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import io.fabric.sdk.android.services.common.AbstractSpiCall;
import io.fabric.sdk.android.services.common.CommonUtils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

class dj {
    /* renamed from: A */
    public static final dl f746A = m807a("mrec_ad_refresh_enabled", Boolean.valueOf(true));
    /* renamed from: B */
    public static final dl f747B = m807a("mrec_ad_refresh_seconds", Long.valueOf(120));
    /* renamed from: C */
    public static final dl f748C = m807a("leader_ad_refresh_enabled", Boolean.valueOf(true));
    /* renamed from: D */
    public static final dl f749D = m807a("leader_ad_refresh_seconds", Long.valueOf(120));
    /* renamed from: E */
    public static final dl f750E = m807a("plugin_version", "");
    /* renamed from: F */
    public static final dl f751F = m807a("ad_preload_enabled", Boolean.valueOf(true));
    /* renamed from: G */
    public static final dl f752G = m807a("ad_resource_caching_enabled", Boolean.valueOf(true));
    /* renamed from: H */
    public static final dl f753H = m807a("fail_ad_load_on_failed_video_cache", Boolean.valueOf(true));
    /* renamed from: I */
    public static final dl f754I = m807a("resource_cache_prefix", "https://vid.applovin.com/,https://pdn.applovin.com/,https://img.applovin.com/,https://d.applovin.com/,https://assets.applovin.com/,https://cdnjs.cloudflare.com/,http://vid.applovin.com/,http://pdn.applovin.com/,http://img.applovin.com/,http://d.applovin.com/,http://assets.applovin.com/,http://cdnjs.cloudflare.com/");
    /* renamed from: J */
    public static final dl f755J = m807a("ad_auto_preload_sizes", "INTER");
    /* renamed from: K */
    public static final dl f756K = m807a("ad_auto_preload_msizes", "INTER");
    /* renamed from: L */
    public static final dl f757L = m807a("ad_auto_preload_incent", Boolean.valueOf(true));
    /* renamed from: M */
    public static final dl f758M = m807a("ad_auto_preload_mincent", Boolean.valueOf(true));
    /* renamed from: N */
    public static final dl f759N = m807a("is_tracking_enabled", Boolean.valueOf(true));
    /* renamed from: O */
    public static final dl f760O = m807a("force_back_button_enabled_always", Boolean.valueOf(false));
    /* renamed from: P */
    public static final dl f761P = m807a("close_fade_in_time", Integer.valueOf(400));
    /* renamed from: Q */
    public static final dl f762Q = m807a("show_close_on_exit", Boolean.valueOf(true));
    /* renamed from: R */
    public static final dl f763R = m807a("text_incent_prompt_title", "Earn a Reward");
    /* renamed from: S */
    public static final dl f764S = m807a("text_incent_prompt_body", "Would you like to watch a video for a reward?");
    /* renamed from: T */
    public static final dl f765T = m807a("text_incent_prompt_yes_option", "Watch Now");
    /* renamed from: U */
    public static final dl f766U = m807a("text_incent_prompt_no_option", "No Thanks");
    /* renamed from: V */
    public static final dl f767V = m807a("text_incent_completion_title", "Video Reward");
    /* renamed from: W */
    public static final dl f768W = m807a("text_incent_completion_body_success", "You have earned a reward!");
    /* renamed from: X */
    public static final dl f769X = m807a("text_incent_completion_body_quota_exceeded", "You have already earned the maximum reward for today.");
    /* renamed from: Y */
    public static final dl f770Y = m807a("text_incent_completion_body_reward_rejected", "Your reward was rejected.");
    /* renamed from: Z */
    public static final dl f771Z = m807a("text_incent_completion_body_network_failure", "We were unable to contact the rewards server. Please try again later.");
    /* renamed from: a */
    public static final dl f772a = m807a("is_disabled", Boolean.valueOf(false));
    public static final dl aA = m807a("close_button_size_video", Integer.valueOf(30));
    public static final dl aB = m807a("close_button_top_margin_graphic", Integer.valueOf(10));
    public static final dl aC = m807a("close_button_right_margin_graphic", Integer.valueOf(10));
    public static final dl aD = m807a("close_button_top_margin_video", Integer.valueOf(8));
    public static final dl aE = m807a("close_button_right_margin_video", Integer.valueOf(4));
    public static final dl aF = m807a("force_back_button_enabled_poststitial", Boolean.valueOf(false));
    public static final dl aG = m807a("force_back_button_enabled_close_button", Boolean.valueOf(false));
    public static final dl aH = m807a("close_button_touch_area", Integer.valueOf(0));
    public static final dl aI = m807a("is_video_skippable", Boolean.valueOf(false));
    public static final dl aJ = m807a("cache_cleanup_enabled", Boolean.valueOf(false));
    public static final dl aK = m807a("cache_file_ttl_seconds", Long.valueOf(86400));
    public static final dl aL = m807a("cache_max_size_mb", Integer.valueOf(-1));
    public static final dl aM = m807a("preload_merge_init_tasks_incent", Boolean.valueOf(true));
    public static final dl aN = m807a("preload_merge_init_tasks_inter", Boolean.valueOf(true));
    public static final dl aO = m807a("preload_merge_init_tasks_mincent", Boolean.valueOf(true));
    public static final dl aP = m807a("preload_merge_init_tasks_minter", Boolean.valueOf(true));
    public static final dl aQ = m807a("submit_postback_timeout", Integer.valueOf(AbstractSpiCall.DEFAULT_TIMEOUT));
    public static final dl aR = m807a("submit_postback_retries", Integer.valueOf(4));
    public static final dl aS = m807a("widget_imp_tracking_delay", Integer.valueOf(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE));
    public static final dl aT = m807a("draw_countdown_clock", Boolean.valueOf(true));
    public static final dl aU = m807a("countdown_clock_size", Integer.valueOf(32));
    public static final dl aV = m807a("countdown_clock_stroke_size", Integer.valueOf(4));
    public static final dl aW = m807a("countdown_clock_text_size", Integer.valueOf(28));
    public static final dl aX = m807a("ad_auto_preload_native", Boolean.valueOf(true));
    public static final dl aY = m807a("widget_fail_on_slot_count_diff", Boolean.valueOf(true));
    public static final dl aZ = m807a("video_zero_length_as_computed", Boolean.valueOf(false));
    public static final dl aa = m807a("text_incent_completion_close_option", "Okay");
    public static final dl ab = m807a("incent_warning_enabled", Boolean.valueOf(false));
    public static final dl ac = m807a("text_incent_warning_title", "Attention!");
    public static final dl ad = m807a("text_incent_warning_body", "You won’t get your reward if the video hasn’t finished.");
    public static final dl ae = m807a("text_incent_warning_close_option", "Close");
    public static final dl af = m807a("text_incent_warning_continue_option", "Keep Watching");
    public static final dl ag = m807a("incent_nonvideo_warning_enabled", Boolean.valueOf(false));
    public static final dl ah = m807a("text_incent_nonvideo_warning_title", "Attention!");
    public static final dl ai = m807a("text_incent_nonvideo_warning_body", "You won’t get your reward if the game hasn’t finished.");
    public static final dl aj = m807a("text_incent_nonvideo_warning_close_option", "Close");
    public static final dl ak = m807a("text_incent_nonvideo_warning_continue_option", "Keep Playing");
    public static final dl al = m807a("show_incent_prepopup", Boolean.valueOf(true));
    public static final dl am = m807a("show_incent_postpopup", Boolean.valueOf(true));
    public static final dl an = m807a("preload_capacity_banner", Integer.valueOf(1));
    public static final dl ao = m807a("preload_capacity_mbanner", Integer.valueOf(1));
    public static final dl ap = m807a("preload_capacity_mrec", Integer.valueOf(1));
    public static final dl aq = m807a("preload_capacity_mmrec", Integer.valueOf(1));
    public static final dl ar = m807a("preload_capacity_inter", Integer.valueOf(1));
    public static final dl as = m807a("preload_capacity_minter", Integer.valueOf(1));
    public static final dl at = m807a("preload_capacity_leader", Integer.valueOf(1));
    public static final dl au = m807a("preload_capacity_mleader", Integer.valueOf(1));
    public static final dl av = m807a("preload_capacity_incent", Integer.valueOf(2));
    public static final dl aw = m807a("preload_capacity_mincent", Integer.valueOf(2));
    public static final dl ax = m807a("dismiss_video_on_error", Boolean.valueOf(true));
    public static final dl ay = m807a("precache_delimiters", ")]',");
    public static final dl az = m807a("close_button_size_graphic", Integer.valueOf(27));
    /* renamed from: b */
    public static final dl f773b = m807a("honor_publisher_settings", Boolean.valueOf(true));
    public static final dl bA = m807a("click_overlay_color", "#66000000");
    public static final dl bB = m807a("click_tracking_retry_count", Integer.valueOf(3));
    public static final dl bC = m807a("click_tracking_retry_delay", Integer.valueOf(CredentialsApi.CREDENTIAL_PICKER_REQUEST_CODE));
    public static final dl bD = m807a("click_tracking_timeout", Integer.valueOf(AbstractSpiCall.DEFAULT_TIMEOUT));
    public static final dl bE = m807a("android_click_spinner_size", Integer.valueOf(50));
    public static final dl bF = m807a("android_click_spinner_style", Integer.valueOf(16973853));
    public static final dl bG = m807a("android_require_external_storage_permission", Boolean.valueOf(true));
    public static final dl bH = m807a("android_drop_nomedia", Boolean.valueOf(true));
    public static final dl bI = m807a("native_auto_cache_preload_resources", Boolean.valueOf(true));
    public static final dl bJ = m807a("video_immersive_mode_enabled", Boolean.valueOf(false));
    public static final dl bK = m807a("sanitize_webview", Boolean.valueOf(false));
    public static final dl bL = m807a("force_rerender", Boolean.valueOf(false));
    public static final dl bM = m807a("webview_package_name", "com.google.android.webview");
    public static final dl bN = m807a("adr", Boolean.valueOf(false));
    public static final dl bO = m807a("hgn", Boolean.valueOf(false));
    public static final dl bP = m807a("inter_display_delay", Long.valueOf(200));
    public static final dl bQ = m807a("volume_normalization_factor", Float.valueOf(6.6666665f));
    public static final dl bR = m807a("lock_specific_orientation", Boolean.valueOf(false));
    public static final dl bS = m807a("video_callbacks_for_incent_nonvideo_ads_enabled", Boolean.valueOf(true));
    public static final dl bT = m807a("user_agent_collection_enabled", Boolean.valueOf(false));
    public static final dl bU = m807a("user_agent_collection_timeout_ms", Long.valueOf(600));
    public static final dl bV = m807a("handle_window_actions", Boolean.valueOf(false));
    public static final dl bW = m807a("soft_buttons_resource_id", "config_showNavigationBar");
    public static final dl bX = m807a("immediate_render", Boolean.valueOf(false));
    public static final dl bY = m807a("video_resume_delay", Long.valueOf(250));
    public static final dl bZ = m807a("force_hide_status_bar_delay_ms", Long.valueOf(0));
    public static final dl ba = m807a("video_countdown_clock_margin", Integer.valueOf(10));
    public static final dl bb = m807a("video_countdown_clock_gravity", Integer.valueOf(83));
    public static final dl bc = m807a("preload_capacity_widget", Integer.valueOf(1));
    public static final dl bd = m807a("widget_latch_timeout_ms", Integer.valueOf(500));
    public static final dl be = m807a("android_gc_on_widget_detach", Boolean.valueOf(true));
    public static final dl bf = m807a("lhs_close_button_video", Boolean.valueOf(false));
    public static final dl bg = m807a("lhs_close_button_graphic", Boolean.valueOf(false));
    public static final dl bh = m807a("lhs_skip_button", Boolean.valueOf(true));
    public static final dl bi = m807a("countdown_toggleable", Boolean.valueOf(false));
    public static final dl bj = m807a("native_batch_precache_count", Integer.valueOf(1));
    public static final dl bk = m807a("mute_controls_enabled", Boolean.valueOf(false));
    public static final dl bl = m807a("allow_user_muting", Boolean.valueOf(true));
    public static final dl bm = m807a("mute_button_size", Integer.valueOf(32));
    public static final dl bn = m807a("mute_button_margin", Integer.valueOf(10));
    public static final dl bo = m807a("mute_button_gravity", Integer.valueOf(85));
    public static final dl bp = m807a("qq", Boolean.valueOf(false));
    public static final dl bq = m807a("hw_accelerate_webviews", Boolean.valueOf(false));
    public static final dl br = m807a("mute_videos", Boolean.valueOf(false));
    public static final dl bs = m807a("show_mute_by_default", Boolean.valueOf(false));
    public static final dl bt = m807a("mute_with_user_settings", Boolean.valueOf(true));
    public static final dl bu = m807a("top_level_events", "landing,checkout,iap");
    public static final dl bv = m807a("events_enabled", Boolean.valueOf(true));
    public static final dl bw = m807a("force_ssl", Boolean.valueOf(false));
    public static final dl bx = m807a("postback_service_max_queue_size", Integer.valueOf(100));
    public static final dl by = m807a("max_postback_attempts", Integer.valueOf(3));
    public static final dl bz = m807a("click_overlay_enabled", Boolean.valueOf(false));
    /* renamed from: c */
    public static final dl f774c = m807a("device_id", "");
    public static final dl cA = m807a("mediation_track_clk", Boolean.valueOf(true));
    public static final dl cB = m807a("mediation_track_err", Boolean.valueOf(true));
    private static final List cC = Arrays.asList(new Class[]{Boolean.class, Float.class, Integer.class, Long.class, String.class});
    private static final List cD = new ArrayList();
    public static final dl ca = m807a("load_ads_if_no_internet", Boolean.valueOf(true));
    public static final dl cb = m807a("display_ads_if_no_internet", Boolean.valueOf(true));
    public static final dl cc = m807a("expandable_close_button_animation_duration_ms", Long.valueOf(300));
    public static final dl cd = m807a("expandable_close_button_size", Integer.valueOf(27));
    public static final dl ce = m807a("expandable_h_close_button_margin", Integer.valueOf(10));
    public static final dl cf = m807a("expandable_t_close_button_margin", Integer.valueOf(10));
    public static final dl cg = m807a("expandable_lhs_close_button", Boolean.valueOf(false));
    public static final dl ch = m807a("expandable_close_button_touch_area", Integer.valueOf(0));
    public static final dl ci = m807a("progress_bar_step", Long.valueOf(25));
    public static final dl cj = m807a("progress_bar_scale", Integer.valueOf(AbstractSpiCall.DEFAULT_TIMEOUT));
    public static final dl ck = m807a("progress_bar_vertical_padding", Integer.valueOf(-8));
    public static final dl cl = m807a("empty_imp", Boolean.valueOf(true));
    public static final dl cm = m807a("vast_max_wrapper_depth", Integer.valueOf(5));
    public static final dl cn = m807a("vast_wrapper_resolution_retry_count", Integer.valueOf(1));
    public static final dl co = m807a("vast_wrapper_resolution_connection_timeout", Integer.valueOf(30000));
    public static final dl cp = m807a("vast_max_response_length", Integer.valueOf(640000));
    public static final dl cq = m807a("vast_video_selection_policy", Integer.valueOf(C0466q.MEDIUM.ordinal()));
    public static final dl cr = m807a("vast_image_html", "<html><head><style>html,body{height:100%;width:100%}body{background-image:url({SOURCE});background-repeat:no-repeat;background-size:contain;background-position:center;}a{position:absolute;top:0;bottom:0;left:0;right:0}</style></head><body><a href=\"applovin://com.applovin.sdk/adservice/track_click_now\"></a></body></html>");
    public static final dl cs = m807a("vast_progress_tracking_countdown_step", Long.valueOf(1000));
    public static final dl ct = m807a("vast_unsupported_video_types", "video/ogg,video/x-flv");
    public static final dl cu = m807a("vast_unsupported_video_extensions", "ogv,flv");
    public static final dl cv = m807a("vast_validate_with_extension_if_no_video_type", Boolean.valueOf(true));
    public static final dl cw = m807a("num_ads_preload_ahead", Integer.valueOf(2));
    public static final dl cx = m807a("mediation_load_last_session_adapters_on_next_init", Boolean.valueOf(true));
    public static final dl cy = m807a("mediation_load_predefined_adapters_on_next_init", Boolean.valueOf(true));
    public static final dl cz = m807a("mediation_track_imp", Boolean.valueOf(true));
    /* renamed from: d */
    public static final dl f775d = m807a("publisher_id", "");
    /* renamed from: e */
    public static final dl f776e = m807a("device_token", "");
    /* renamed from: f */
    public static final dl f777f = m807a("submit_data_retry_count", Integer.valueOf(1));
    /* renamed from: g */
    public static final dl f778g = m807a("vr_retry_count", Integer.valueOf(1));
    /* renamed from: h */
    public static final dl f779h = m807a("fetch_ad_retry_count", Integer.valueOf(1));
    /* renamed from: i */
    public static final dl f780i = m807a("is_verbose_logging", Boolean.valueOf(false));
    /* renamed from: j */
    public static final dl f781j = m807a("api_endpoint", "https://d.applovin.com/");
    /* renamed from: k */
    public static final dl f782k = m807a("adserver_endpoint", "https://a.applovin.com/");
    /* renamed from: l */
    public static final dl f783l = m807a("mediation_tracking_endpoint", "https://rt.applovin.com/med");
    /* renamed from: m */
    public static final dl f784m = m807a("event_tracking_endpoint", "https://rt.applovin.com/pix");
    /* renamed from: n */
    public static final dl f785n = m807a("api_backup_endpoint", "https://d.applvn.com/");
    /* renamed from: o */
    public static final dl f786o = m807a("adserver_backup_endpoint", "https://a.applvn.com/");
    /* renamed from: p */
    public static final dl f787p = m807a("event_tracking_backup_endpoint", "https://rt.applvn.com/pix");
    /* renamed from: q */
    public static final dl f788q = m807a("get_retry_delay", Long.valueOf(10000));
    /* renamed from: r */
    public static final dl f789r = m807a("hash_algorithm", CommonUtils.SHA1_INSTANCE);
    /* renamed from: s */
    public static final dl f790s = m807a("short_hash_size", Integer.valueOf(16));
    /* renamed from: t */
    public static final dl f791t = m807a("http_connection_timeout", Integer.valueOf(30000));
    /* renamed from: u */
    public static final dl f792u = m807a("fetch_ad_connection_timeout", Integer.valueOf(30000));
    /* renamed from: v */
    public static final dl f793v = m807a("http_socket_timeout", Integer.valueOf(20000));
    /* renamed from: w */
    public static final dl f794w = m807a("ad_session_minutes", Integer.valueOf(60));
    /* renamed from: x */
    public static final dl f795x = m807a("ad_request_parameters", "");
    /* renamed from: y */
    public static final dl f796y = m807a("ad_refresh_enabled", Boolean.valueOf(true));
    /* renamed from: z */
    public static final dl f797z = m807a("ad_refresh_seconds", Long.valueOf(120));

    /* renamed from: a */
    private static dl m807a(String str, Object obj) {
        if (obj == null) {
            throw new IllegalArgumentException("No default value specified");
        } else if (cC.contains(obj.getClass())) {
            dl dlVar = new dl(str, obj);
            cD.add(dlVar);
            return dlVar;
        } else {
            throw new IllegalArgumentException("Unsupported value type: " + obj.getClass());
        }
    }

    /* renamed from: a */
    public static Collection m808a() {
        return Collections.unmodifiableList(cD);
    }

    /* renamed from: b */
    public static int m809b() {
        return cD.size();
    }
}
