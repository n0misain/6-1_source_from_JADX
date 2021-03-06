package com.mikepenz.fontawesome_typeface_library;

import android.content.Context;
import android.graphics.Typeface;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class FontAwesome implements ITypeface {
    private static final String TTF_FILE = "fontawesome-font-v4.7.ttf";
    private static HashMap<String, Character> mChars;
    private static Typeface typeface = null;

    public enum Icon implements IIcon {
        faw_glass(''),
        faw_music(''),
        faw_search(''),
        faw_envelope_o(''),
        faw_heart(''),
        faw_star(''),
        faw_star_o(''),
        faw_user(''),
        faw_film(''),
        faw_th_large(''),
        faw_th(''),
        faw_th_list(''),
        faw_check(''),
        faw_times(''),
        faw_search_plus(''),
        faw_search_minus(''),
        faw_power_off(''),
        faw_signal(''),
        faw_cog(''),
        faw_trash_o(''),
        faw_home(''),
        faw_file_o(''),
        faw_clock_o(''),
        faw_road(''),
        faw_download(''),
        faw_arrow_circle_o_down(''),
        faw_arrow_circle_o_up(''),
        faw_inbox(''),
        faw_play_circle_o(''),
        faw_repeat(''),
        faw_refresh(''),
        faw_list_alt(''),
        faw_lock(''),
        faw_flag(''),
        faw_headphones(''),
        faw_volume_off(''),
        faw_volume_down(''),
        faw_volume_up(''),
        faw_qrcode(''),
        faw_barcode(''),
        faw_tag(''),
        faw_tags(''),
        faw_book(''),
        faw_bookmark(''),
        faw_print(''),
        faw_camera(''),
        faw_font(''),
        faw_bold(''),
        faw_italic(''),
        faw_text_height(''),
        faw_text_width(''),
        faw_align_left(''),
        faw_align_center(''),
        faw_align_right(''),
        faw_align_justify(''),
        faw_list(''),
        faw_outdent(''),
        faw_indent(''),
        faw_video_camera(''),
        faw_picture_o(''),
        faw_pencil(''),
        faw_map_marker(''),
        faw_adjust(''),
        faw_tint(''),
        faw_pencil_square_o(''),
        faw_share_square_o(''),
        faw_check_square_o(''),
        faw_arrows(''),
        faw_step_backward(''),
        faw_fast_backward(''),
        faw_backward(''),
        faw_play(''),
        faw_pause(''),
        faw_stop(''),
        faw_forward(''),
        faw_fast_forward(''),
        faw_step_forward(''),
        faw_eject(''),
        faw_chevron_left(''),
        faw_chevron_right(''),
        faw_plus_circle(''),
        faw_minus_circle(''),
        faw_times_circle(''),
        faw_check_circle(''),
        faw_question_circle(''),
        faw_info_circle(''),
        faw_crosshairs(''),
        faw_times_circle_o(''),
        faw_check_circle_o(''),
        faw_ban(''),
        faw_arrow_left(''),
        faw_arrow_right(''),
        faw_arrow_up(''),
        faw_arrow_down(''),
        faw_share(''),
        faw_expand(''),
        faw_compress(''),
        faw_plus(''),
        faw_minus(''),
        faw_asterisk(''),
        faw_exclamation_circle(''),
        faw_gift(''),
        faw_leaf(''),
        faw_fire(''),
        faw_eye(''),
        faw_eye_slash(''),
        faw_exclamation_triangle(''),
        faw_plane(''),
        faw_calendar(''),
        faw_random(''),
        faw_comment(''),
        faw_magnet(''),
        faw_chevron_up(''),
        faw_chevron_down(''),
        faw_retweet(''),
        faw_shopping_cart(''),
        faw_folder(''),
        faw_folder_open(''),
        faw_arrows_v(''),
        faw_arrows_h(''),
        faw_bar_chart(''),
        faw_twitter_square(''),
        faw_facebook_square(''),
        faw_camera_retro(''),
        faw_key(''),
        faw_cogs(''),
        faw_comments(''),
        faw_thumbs_o_up(''),
        faw_thumbs_o_down(''),
        faw_star_half(''),
        faw_heart_o(''),
        faw_sign_out(''),
        faw_linkedin_square(''),
        faw_thumb_tack(''),
        faw_external_link(''),
        faw_sign_in(''),
        faw_trophy(''),
        faw_github_square(''),
        faw_upload(''),
        faw_lemon_o(''),
        faw_phone(''),
        faw_square_o(''),
        faw_bookmark_o(''),
        faw_phone_square(''),
        faw_twitter(''),
        faw_facebook(''),
        faw_github(''),
        faw_unlock(''),
        faw_credit_card(''),
        faw_rss(''),
        faw_hdd_o(''),
        faw_bullhorn(''),
        faw_bell_o(''),
        faw_certificate(''),
        faw_hand_o_right(''),
        faw_hand_o_left(''),
        faw_hand_o_up(''),
        faw_hand_o_down(''),
        faw_arrow_circle_left(''),
        faw_arrow_circle_right(''),
        faw_arrow_circle_up(''),
        faw_arrow_circle_down(''),
        faw_globe(''),
        faw_wrench(''),
        faw_tasks(''),
        faw_filter(''),
        faw_briefcase(''),
        faw_arrows_alt(''),
        faw_users(''),
        faw_link(''),
        faw_cloud(''),
        faw_flask(''),
        faw_scissors(''),
        faw_files_o(''),
        faw_paperclip(''),
        faw_floppy_o(''),
        faw_square(''),
        faw_bars(''),
        faw_list_ul(''),
        faw_list_ol(''),
        faw_strikethrough(''),
        faw_underline(''),
        faw_table(''),
        faw_magic(''),
        faw_truck(''),
        faw_pinterest(''),
        faw_pinterest_square(''),
        faw_google_plus_square(''),
        faw_google_plus(''),
        faw_money(''),
        faw_caret_down(''),
        faw_caret_up(''),
        faw_caret_left(''),
        faw_caret_right(''),
        faw_columns(''),
        faw_sort(''),
        faw_sort_desc(''),
        faw_sort_asc(''),
        faw_envelope(''),
        faw_linkedin(''),
        faw_undo(''),
        faw_gavel(''),
        faw_tachometer(''),
        faw_comment_o(''),
        faw_comments_o(''),
        faw_bolt(''),
        faw_sitemap(''),
        faw_umbrella(''),
        faw_clipboard(''),
        faw_lightbulb_o(''),
        faw_exchange(''),
        faw_cloud_download(''),
        faw_cloud_upload(''),
        faw_user_md(''),
        faw_stethoscope(''),
        faw_suitcase(''),
        faw_bell(''),
        faw_coffee(''),
        faw_cutlery(''),
        faw_file_text_o(''),
        faw_building_o(''),
        faw_hospital_o(''),
        faw_ambulance(''),
        faw_medkit(''),
        faw_fighter_jet(''),
        faw_beer(''),
        faw_h_square(''),
        faw_plus_square(''),
        faw_angle_double_left(''),
        faw_angle_double_right(''),
        faw_angle_double_up(''),
        faw_angle_double_down(''),
        faw_angle_left(''),
        faw_angle_right(''),
        faw_angle_up(''),
        faw_angle_down(''),
        faw_desktop(''),
        faw_laptop(''),
        faw_tablet(''),
        faw_mobile(''),
        faw_circle_o(''),
        faw_quote_left(''),
        faw_quote_right(''),
        faw_spinner(''),
        faw_circle(''),
        faw_reply(''),
        faw_github_alt(''),
        faw_folder_o(''),
        faw_folder_open_o(''),
        faw_smile_o(''),
        faw_frown_o(''),
        faw_meh_o(''),
        faw_gamepad(''),
        faw_keyboard_o(''),
        faw_flag_o(''),
        faw_flag_checkered(''),
        faw_terminal(''),
        faw_code(''),
        faw_reply_all(''),
        faw_star_half_o(''),
        faw_location_arrow(''),
        faw_crop(''),
        faw_code_fork(''),
        faw_chain_broken(''),
        faw_question(''),
        faw_info(''),
        faw_exclamation(''),
        faw_superscript(''),
        faw_subscript(''),
        faw_eraser(''),
        faw_puzzle_piece(''),
        faw_microphone(''),
        faw_microphone_slash(''),
        faw_shield(''),
        faw_calendar_o(''),
        faw_fire_extinguisher(''),
        faw_rocket(''),
        faw_maxcdn(''),
        faw_chevron_circle_left(''),
        faw_chevron_circle_right(''),
        faw_chevron_circle_up(''),
        faw_chevron_circle_down(''),
        faw_html5(''),
        faw_css3(''),
        faw_anchor(''),
        faw_unlock_alt(''),
        faw_bullseye(''),
        faw_ellipsis_h(''),
        faw_ellipsis_v(''),
        faw_rss_square(''),
        faw_play_circle(''),
        faw_ticket(''),
        faw_minus_square(''),
        faw_minus_square_o(''),
        faw_level_up(''),
        faw_level_down(''),
        faw_check_square(''),
        faw_pencil_square(''),
        faw_external_link_square(''),
        faw_share_square(''),
        faw_compass(''),
        faw_caret_square_o_down(''),
        faw_caret_square_o_up(''),
        faw_caret_square_o_right(''),
        faw_eur(''),
        faw_gbp(''),
        faw_usd(''),
        faw_inr(''),
        faw_jpy(''),
        faw_rub(''),
        faw_krw(''),
        faw_btc(''),
        faw_file(''),
        faw_file_text(''),
        faw_sort_alpha_asc(''),
        faw_sort_alpha_desc(''),
        faw_sort_amount_asc(''),
        faw_sort_amount_desc(''),
        faw_sort_numeric_asc(''),
        faw_sort_numeric_desc(''),
        faw_thumbs_up(''),
        faw_thumbs_down(''),
        faw_youtube_square(''),
        faw_youtube(''),
        faw_xing(''),
        faw_xing_square(''),
        faw_youtube_play(''),
        faw_dropbox(''),
        faw_stack_overflow(''),
        faw_instagram(''),
        faw_flickr(''),
        faw_adn(''),
        faw_bitbucket(''),
        faw_bitbucket_square(''),
        faw_tumblr(''),
        faw_tumblr_square(''),
        faw_long_arrow_down(''),
        faw_long_arrow_up(''),
        faw_long_arrow_left(''),
        faw_long_arrow_right(''),
        faw_apple(''),
        faw_windows(''),
        faw_android(''),
        faw_linux(''),
        faw_dribbble(''),
        faw_skype(''),
        faw_foursquare(''),
        faw_trello(''),
        faw_female(''),
        faw_male(''),
        faw_gratipay(''),
        faw_sun_o(''),
        faw_moon_o(''),
        faw_archive(''),
        faw_bug(''),
        faw_vk(''),
        faw_weibo(''),
        faw_renren(''),
        faw_pagelines(''),
        faw_stack_exchange(''),
        faw_arrow_circle_o_right(''),
        faw_arrow_circle_o_left(''),
        faw_caret_square_o_left(''),
        faw_dot_circle_o(''),
        faw_wheelchair(''),
        faw_vimeo_square(''),
        faw_try(''),
        faw_plus_square_o(''),
        faw_space_shuttle(''),
        faw_slack(''),
        faw_envelope_square(''),
        faw_wordpress(''),
        faw_openid(''),
        faw_university(''),
        faw_graduation_cap(''),
        faw_yahoo(''),
        faw_google(''),
        faw_reddit(''),
        faw_reddit_square(''),
        faw_stumbleupon_circle(''),
        faw_stumbleupon(''),
        faw_delicious(''),
        faw_digg(''),
        faw_pied_piper_pp(''),
        faw_pied_piper_alt(''),
        faw_drupal(''),
        faw_joomla(''),
        faw_language(''),
        faw_fax(''),
        faw_building(''),
        faw_child(''),
        faw_paw(''),
        faw_spoon(''),
        faw_cube(''),
        faw_cubes(''),
        faw_behance(''),
        faw_behance_square(''),
        faw_steam(''),
        faw_steam_square(''),
        faw_recycle(''),
        faw_car(''),
        faw_taxi(''),
        faw_tree(''),
        faw_spotify(''),
        faw_deviantart(''),
        faw_soundcloud(''),
        faw_database(''),
        faw_file_pdf_o(''),
        faw_file_word_o(''),
        faw_file_excel_o(''),
        faw_file_powerpoint_o(''),
        faw_file_image_o(''),
        faw_file_archive_o(''),
        faw_file_audio_o(''),
        faw_file_video_o(''),
        faw_file_code_o(''),
        faw_vine(''),
        faw_codepen(''),
        faw_jsfiddle(''),
        faw_life_ring(''),
        faw_circle_o_notch(''),
        faw_rebel(''),
        faw_empire(''),
        faw_git_square(''),
        faw_git(''),
        faw_hacker_news(''),
        faw_tencent_weibo(''),
        faw_qq(''),
        faw_weixin(''),
        faw_paper_plane(''),
        faw_paper_plane_o(''),
        faw_history(''),
        faw_circle_thin(''),
        faw_header(''),
        faw_paragraph(''),
        faw_sliders(''),
        faw_share_alt(''),
        faw_share_alt_square(''),
        faw_bomb(''),
        faw_futbol_o(''),
        faw_tty(''),
        faw_binoculars(''),
        faw_plug(''),
        faw_slideshare(''),
        faw_twitch(''),
        faw_yelp(''),
        faw_newspaper_o(''),
        faw_wifi(''),
        faw_calculator(''),
        faw_paypal(''),
        faw_google_wallet(''),
        faw_cc_visa(''),
        faw_cc_mastercard(''),
        faw_cc_discover(''),
        faw_cc_amex(''),
        faw_cc_paypal(''),
        faw_cc_stripe(''),
        faw_bell_slash(''),
        faw_bell_slash_o(''),
        faw_trash(''),
        faw_copyright(''),
        faw_at(''),
        faw_eyedropper(''),
        faw_paint_brush(''),
        faw_birthday_cake(''),
        faw_area_chart(''),
        faw_pie_chart(''),
        faw_line_chart(''),
        faw_lastfm(''),
        faw_lastfm_square(''),
        faw_toggle_off(''),
        faw_toggle_on(''),
        faw_bicycle(''),
        faw_bus(''),
        faw_ioxhost(''),
        faw_angellist(''),
        faw_cc(''),
        faw_ils(''),
        faw_meanpath(''),
        faw_buysellads(''),
        faw_connectdevelop(''),
        faw_dashcube(''),
        faw_forumbee(''),
        faw_leanpub(''),
        faw_sellsy(''),
        faw_shirtsinbulk(''),
        faw_simplybuilt(''),
        faw_skyatlas(''),
        faw_cart_plus(''),
        faw_cart_arrow_down(''),
        faw_diamond(''),
        faw_ship(''),
        faw_user_secret(''),
        faw_motorcycle(''),
        faw_street_view(''),
        faw_heartbeat(''),
        faw_venus(''),
        faw_mars(''),
        faw_mercury(''),
        faw_transgender(''),
        faw_transgender_alt(''),
        faw_venus_double(''),
        faw_mars_double(''),
        faw_venus_mars(''),
        faw_mars_stroke(''),
        faw_mars_stroke_v(''),
        faw_mars_stroke_h(''),
        faw_neuter(''),
        faw_genderless(''),
        faw_facebook_official(''),
        faw_pinterest_p(''),
        faw_whatsapp(''),
        faw_server(''),
        faw_user_plus(''),
        faw_user_times(''),
        faw_bed(''),
        faw_viacoin(''),
        faw_train(''),
        faw_subway(''),
        faw_medium(''),
        faw_y_combinator(''),
        faw_optin_monster(''),
        faw_opencart(''),
        faw_expeditedssl(''),
        faw_battery_full(''),
        faw_battery_three_quarters(''),
        faw_battery_half(''),
        faw_battery_quarter(''),
        faw_battery_empty(''),
        faw_mouse_pointer(''),
        faw_i_cursor(''),
        faw_object_group(''),
        faw_object_ungroup(''),
        faw_sticky_note(''),
        faw_sticky_note_o(''),
        faw_cc_jcb(''),
        faw_cc_diners_club(''),
        faw_clone(''),
        faw_balance_scale(''),
        faw_hourglass_o(''),
        faw_hourglass_start(''),
        faw_hourglass_half(''),
        faw_hourglass_end(''),
        faw_hourglass(''),
        faw_hand_rock_o(''),
        faw_hand_paper_o(''),
        faw_hand_scissors_o(''),
        faw_hand_lizard_o(''),
        faw_hand_spock_o(''),
        faw_hand_pointer_o(''),
        faw_hand_peace_o(''),
        faw_trademark(''),
        faw_registered(''),
        faw_creative_commons(''),
        faw_gg(''),
        faw_gg_circle(''),
        faw_tripadvisor(''),
        faw_odnoklassniki(''),
        faw_odnoklassniki_square(''),
        faw_get_pocket(''),
        faw_wikipedia_w(''),
        faw_safari(''),
        faw_chrome(''),
        faw_firefox(''),
        faw_opera(''),
        faw_internet_explorer(''),
        faw_television(''),
        faw_contao(''),
        faw_500px(''),
        faw_amazon(''),
        faw_calendar_plus_o(''),
        faw_calendar_minus_o(''),
        faw_calendar_times_o(''),
        faw_calendar_check_o(''),
        faw_industry(''),
        faw_map_pin(''),
        faw_map_signs(''),
        faw_map_o(''),
        faw_map(''),
        faw_commenting(''),
        faw_commenting_o(''),
        faw_houzz(''),
        faw_vimeo(''),
        faw_black_tie(''),
        faw_fonticons(''),
        faw_reddit_alien(''),
        faw_edge(''),
        faw_credit_card_alt(''),
        faw_codiepie(''),
        faw_modx(''),
        faw_fort_awesome(''),
        faw_usb(''),
        faw_product_hunt(''),
        faw_mixcloud(''),
        faw_scribd(''),
        faw_pause_circle(''),
        faw_pause_circle_o(''),
        faw_stop_circle(''),
        faw_stop_circle_o(''),
        faw_shopping_bag(''),
        faw_shopping_basket(''),
        faw_hashtag(''),
        faw_bluetooth(''),
        faw_bluetooth_b(''),
        faw_percent(''),
        faw_gitlab(''),
        faw_wpbeginner(''),
        faw_wpforms(''),
        faw_envira(''),
        faw_universal_access(''),
        faw_wheelchair_alt(''),
        faw_question_circle_o(''),
        faw_blind(''),
        faw_audio_description(''),
        faw_volume_control_phone(''),
        faw_braille(''),
        faw_assistive_listening_systems(''),
        faw_american_sign_language_interpreting(''),
        faw_deaf(''),
        faw_glide(''),
        faw_glide_g(''),
        faw_sign_language(''),
        faw_low_vision(''),
        faw_viadeo(''),
        faw_viadeo_square(''),
        faw_snapchat(''),
        faw_snapchat_ghost(''),
        faw_snapchat_square(''),
        faw_pied_piper(''),
        faw_first_order(''),
        faw_yoast(''),
        faw_themeisle(''),
        faw_google_plus_official(''),
        faw_font_awesome(''),
        faw_handshake_o(''),
        faw_envelope_open(''),
        faw_envelope_open_o(''),
        faw_linode(''),
        faw_address_book(''),
        faw_address_book_o(''),
        faw_address_card(''),
        faw_address_card_o(''),
        faw_user_circle(''),
        faw_user_circle_o(''),
        faw_user_o(''),
        faw_id_badge(''),
        faw_id_card(''),
        faw_id_card_o(''),
        faw_quora(''),
        faw_free_code_camp(''),
        faw_telegram(''),
        faw_thermometer_full(''),
        faw_thermometer_three_quarters(''),
        faw_thermometer_half(''),
        faw_thermometer_quarter(''),
        faw_thermometer_empty(''),
        faw_shower(''),
        faw_bath(''),
        faw_podcast(''),
        faw_window_maximize(''),
        faw_window_minimize(''),
        faw_window_restore(''),
        faw_window_close(''),
        faw_window_close_o(''),
        faw_bandcamp(''),
        faw_grav(''),
        faw_etsy(''),
        faw_imdb(''),
        faw_ravelry(''),
        faw_eercast(''),
        faw_microchip(''),
        faw_snowflake_o(''),
        faw_superpowers(''),
        faw_wpexplorer(''),
        faw_meetup('');
        
        private static ITypeface typeface;
        char character;

        private Icon(char character) {
            this.character = character;
        }

        public String getFormattedName() {
            return "{" + name() + "}";
        }

        public char getCharacter() {
            return this.character;
        }

        public String getName() {
            return name();
        }

        public ITypeface getTypeface() {
            if (typeface == null) {
                typeface = new FontAwesome();
            }
            return typeface;
        }
    }

    public IIcon getIcon(String key) {
        return Icon.valueOf(key);
    }

    public HashMap<String, Character> getCharacters() {
        if (mChars == null) {
            HashMap<String, Character> aChars = new HashMap();
            for (Icon v : Icon.values()) {
                aChars.put(v.name(), Character.valueOf(v.character));
            }
            mChars = aChars;
        }
        return mChars;
    }

    public String getMappingPrefix() {
        return "faw";
    }

    public String getFontName() {
        return "FontAwesome";
    }

    public String getVersion() {
        return "4.7.0.0";
    }

    public int getIconCount() {
        return mChars.size();
    }

    public Collection<String> getIcons() {
        Collection<String> icons = new LinkedList();
        for (Icon value : Icon.values()) {
            icons.add(value.name());
        }
        return icons;
    }

    public String getAuthor() {
        return "Dave Gandy";
    }

    public String getUrl() {
        return "https://github.com/FortAwesome/Font-Awesome";
    }

    public String getDescription() {
        return "Font Awesome is a full suite of 675 pictographic icons for easy scalable vector graphics on websites, created and maintained by Dave Gandy. Stay up to date @fontawesome.";
    }

    public String getLicense() {
        return "SIL OFL 1.1";
    }

    public String getLicenseUrl() {
        return "http://scripts.sil.org/OFL";
    }

    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/fontawesome-font-v4.7.ttf");
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }
}
