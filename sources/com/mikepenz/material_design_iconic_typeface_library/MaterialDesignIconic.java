package com.mikepenz.material_design_iconic_typeface_library;

import android.content.Context;
import android.graphics.Typeface;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class MaterialDesignIconic implements ITypeface {
    private static final String TTF_FILE = "material-design-iconic-font-v2.2.0.ttf";
    private static HashMap<String, Character> mChars;
    private static Typeface typeface = null;

    public enum Icon implements IIcon {
        gmi_3d_rotation(''),
        gmi_airplane_off(''),
        gmi_airplane(''),
        gmi_album(''),
        gmi_archive(''),
        gmi_assignment_account(''),
        gmi_assignment_alert(''),
        gmi_assignment_check(''),
        gmi_assignment_o(''),
        gmi_assignment_return(''),
        gmi_assignment_returned(''),
        gmi_assignment(''),
        gmi_attachment_alt(''),
        gmi_attachment(''),
        gmi_audio(''),
        gmi_badge_check(''),
        gmi_balance_wallet(''),
        gmi_balance(''),
        gmi_battery_alert(''),
        gmi_battery_flash(''),
        gmi_battery_unknown(''),
        gmi_battery(''),
        gmi_bike(''),
        gmi_block_alt(''),
        gmi_block(''),
        gmi_boat(''),
        gmi_book_image(''),
        gmi_book(''),
        gmi_bookmark_outline(''),
        gmi_bookmark(''),
        gmi_brush(''),
        gmi_bug(''),
        gmi_bus(''),
        gmi_cake(''),
        gmi_car_taxi(''),
        gmi_car_wash(''),
        gmi_car(''),
        gmi_card_giftcard(''),
        gmi_card_membership(''),
        gmi_card_travel(''),
        gmi_card(''),
        gmi_case_check(''),
        gmi_case_download(''),
        gmi_case_play(''),
        gmi_case(''),
        gmi_cast_connected(''),
        gmi_cast(''),
        gmi_chart_donut(''),
        gmi_chart(''),
        gmi_city_alt(''),
        gmi_city(''),
        gmi_close_circle_o(''),
        gmi_close_circle(''),
        gmi_close(''),
        gmi_cocktail(''),
        gmi_code_setting(''),
        gmi_code_smartphone(''),
        gmi_code(''),
        gmi_coffee(''),
        gmi_collection_bookmark(''),
        gmi_collection_case_play(''),
        gmi_collection_folder_image(''),
        gmi_collection_image_o(''),
        gmi_collection_image(''),
        gmi_collection_item_1(''),
        gmi_collection_item_2(''),
        gmi_collection_item_3(''),
        gmi_collection_item_4(''),
        gmi_collection_item_5(''),
        gmi_collection_item_6(''),
        gmi_collection_item_7(''),
        gmi_collection_item_8(''),
        gmi_collection_item_9_plus(''),
        gmi_collection_item_9(''),
        gmi_collection_item(''),
        gmi_collection_music(''),
        gmi_collection_pdf(''),
        gmi_collection_plus(''),
        gmi_collection_speaker(''),
        gmi_collection_text(''),
        gmi_collection_video(''),
        gmi_compass(''),
        gmi_cutlery(''),
        gmi_delete(''),
        gmi_dialpad(''),
        gmi_dns(''),
        gmi_drink(''),
        gmi_edit(''),
        gmi_email_open(''),
        gmi_email(''),
        gmi_eye_off(''),
        gmi_eye(''),
        gmi_eyedropper(''),
        gmi_favorite_outline(''),
        gmi_favorite(''),
        gmi_filter_list(''),
        gmi_fire(''),
        gmi_flag(''),
        gmi_flare(''),
        gmi_flash_auto(''),
        gmi_flash_off(''),
        gmi_flash(''),
        gmi_flip(''),
        gmi_flower_alt(''),
        gmi_flower(''),
        gmi_font(''),
        gmi_fullscreen_alt(''),
        gmi_fullscreen_exit(''),
        gmi_fullscreen(''),
        gmi_functions(''),
        gmi_gas_station(''),
        gmi_gesture(''),
        gmi_globe_alt(''),
        gmi_globe_lock(''),
        gmi_globe(''),
        gmi_graduation_cap(''),
        gmi_home(''),
        gmi_hospital_alt(''),
        gmi_hospital(''),
        gmi_hotel(''),
        gmi_hourglass_alt(''),
        gmi_hourglass_outline(''),
        gmi_hourglass(''),
        gmi_http(''),
        gmi_image_alt(''),
        gmi_image_o(''),
        gmi_image(''),
        gmi_inbox(''),
        gmi_invert_colors_off(''),
        gmi_invert_colors(''),
        gmi_key(''),
        gmi_label_alt_outline(''),
        gmi_label_alt(''),
        gmi_label_heart(''),
        gmi_label(''),
        gmi_labels(''),
        gmi_lamp(''),
        gmi_landscape(''),
        gmi_layers_off(''),
        gmi_layers(''),
        gmi_library(''),
        gmi_link(''),
        gmi_lock_open(''),
        gmi_lock_outline(''),
        gmi_lock(''),
        gmi_mail_reply_all(''),
        gmi_mail_reply(''),
        gmi_mail_send(''),
        gmi_mall(''),
        gmi_map(''),
        gmi_menu(''),
        gmi_money_box(''),
        gmi_money_off(''),
        gmi_money(''),
        gmi_more_vert(''),
        gmi_more(''),
        gmi_movie_alt(''),
        gmi_movie(''),
        gmi_nature_people(''),
        gmi_nature(''),
        gmi_navigation(''),
        gmi_open_in_browser(''),
        gmi_open_in_new(''),
        gmi_palette(''),
        gmi_parking(''),
        gmi_pin_account(''),
        gmi_pin_assistant(''),
        gmi_pin_drop(''),
        gmi_pin_help(''),
        gmi_pin_off(''),
        gmi_pin(''),
        gmi_pizza(''),
        gmi_plaster(''),
        gmi_power_setting(''),
        gmi_power(''),
        gmi_print(''),
        gmi_puzzle_piece(''),
        gmi_quote(''),
        gmi_railway(''),
        gmi_receipt(''),
        gmi_refresh_alt(''),
        gmi_refresh_sync_alert(''),
        gmi_refresh_sync_off(''),
        gmi_refresh_sync(''),
        gmi_refresh(''),
        gmi_roller(''),
        gmi_ruler(''),
        gmi_scissors(''),
        gmi_screen_rotation_lock(''),
        gmi_screen_rotation(''),
        gmi_search_for(''),
        gmi_search_in_file(''),
        gmi_search_in_page(''),
        gmi_search_replace(''),
        gmi_search(''),
        gmi_seat(''),
        gmi_settings_square(''),
        gmi_settings(''),
        gmi_shield_check(''),
        gmi_shield_security(''),
        gmi_shopping_basket(''),
        gmi_shopping_cart_plus(''),
        gmi_shopping_cart(''),
        gmi_sign_in(''),
        gmi_sort_amount_asc(''),
        gmi_sort_amount_desc(''),
        gmi_sort_asc(''),
        gmi_sort_desc(''),
        gmi_spellcheck(''),
        gmi_storage(''),
        gmi_store_24(''),
        gmi_store(''),
        gmi_subway(''),
        gmi_sun(''),
        gmi_tab_unselected(''),
        gmi_tab(''),
        gmi_tag_close(''),
        gmi_tag_more(''),
        gmi_tag(''),
        gmi_thumb_down(''),
        gmi_thumb_up_down(''),
        gmi_thumb_up(''),
        gmi_ticket_star(''),
        gmi_toll(''),
        gmi_toys(''),
        gmi_traffic(''),
        gmi_translate(''),
        gmi_triangle_down(''),
        gmi_triangle_up(''),
        gmi_truck(''),
        gmi_turning_sign(''),
        gmi_wallpaper(''),
        gmi_washing_machine(''),
        gmi_window_maximize(''),
        gmi_window_minimize(''),
        gmi_window_restore(''),
        gmi_wrench(''),
        gmi_zoom_in(''),
        gmi_zoom_out(''),
        gmi_alert_circle_o(''),
        gmi_alert_circle(''),
        gmi_alert_octagon(''),
        gmi_alert_polygon(''),
        gmi_alert_triangle(''),
        gmi_help_outline(''),
        gmi_help(''),
        gmi_info_outline(''),
        gmi_info(''),
        gmi_notifications_active(''),
        gmi_notifications_add(''),
        gmi_notifications_none(''),
        gmi_notifications_off(''),
        gmi_notifications_paused(''),
        gmi_notifications(''),
        gmi_account_add(''),
        gmi_account_box_mail(''),
        gmi_account_box_o(''),
        gmi_account_box_phone(''),
        gmi_account_box(''),
        gmi_account_calendar(''),
        gmi_account_circle(''),
        gmi_account_o(''),
        gmi_account(''),
        gmi_accounts_add(''),
        gmi_accounts_alt(''),
        gmi_accounts_list_alt(''),
        gmi_accounts_list(''),
        gmi_accounts_outline(''),
        gmi_accounts(''),
        gmi_face(''),
        gmi_female(''),
        gmi_male_alt(''),
        gmi_male_female(''),
        gmi_male(''),
        gmi_mood_bad(''),
        gmi_mood(''),
        gmi_run(''),
        gmi_walk(''),
        gmi_cloud_box(''),
        gmi_cloud_circle(''),
        gmi_cloud_done(''),
        gmi_cloud_download(''),
        gmi_cloud_off(''),
        gmi_cloud_outline_alt(''),
        gmi_cloud_outline(''),
        gmi_cloud_upload(''),
        gmi_cloud(''),
        gmi_download(''),
        gmi_file_plus(''),
        gmi_file_text(''),
        gmi_file(''),
        gmi_folder_outline(''),
        gmi_folder_person(''),
        gmi_folder_star_alt(''),
        gmi_folder_star(''),
        gmi_folder(''),
        gmi_gif(''),
        gmi_upload(''),
        gmi_border_all(''),
        gmi_border_bottom(''),
        gmi_border_clear(''),
        gmi_border_color(''),
        gmi_border_horizontal(''),
        gmi_border_inner(''),
        gmi_border_left(''),
        gmi_border_outer(''),
        gmi_border_right(''),
        gmi_border_style(''),
        gmi_border_top(''),
        gmi_border_vertical(''),
        gmi_copy(''),
        gmi_crop(''),
        gmi_format_align_center(''),
        gmi_format_align_justify(''),
        gmi_format_align_left(''),
        gmi_format_align_right(''),
        gmi_format_bold(''),
        gmi_format_clear_all(''),
        gmi_format_clear(''),
        gmi_format_color_fill(''),
        gmi_format_color_reset(''),
        gmi_format_color_text(''),
        gmi_format_indent_decrease(''),
        gmi_format_indent_increase(''),
        gmi_format_italic(''),
        gmi_format_line_spacing(''),
        gmi_format_list_bulleted(''),
        gmi_format_list_numbered(''),
        gmi_format_ltr(''),
        gmi_format_rtl(''),
        gmi_format_size(''),
        gmi_format_strikethrough_s(''),
        gmi_format_strikethrough(''),
        gmi_format_subject(''),
        gmi_format_underlined(''),
        gmi_format_valign_bottom(''),
        gmi_format_valign_center(''),
        gmi_format_valign_top(''),
        gmi_redo(''),
        gmi_select_all(''),
        gmi_space_bar(''),
        gmi_text_format(''),
        gmi_transform(''),
        gmi_undo(''),
        gmi_wrap_text(''),
        gmi_comment_alert(''),
        gmi_comment_alt_text(''),
        gmi_comment_alt(''),
        gmi_comment_edit(''),
        gmi_comment_image(''),
        gmi_comment_list(''),
        gmi_comment_more(''),
        gmi_comment_outline(''),
        gmi_comment_text_alt(''),
        gmi_comment_text(''),
        gmi_comment_video(''),
        gmi_comment(''),
        gmi_comments(''),
        gmi_check_all(''),
        gmi_check_circle_u(''),
        gmi_check_circle(''),
        gmi_check_square(''),
        gmi_check(''),
        gmi_circle_o(''),
        gmi_circle(''),
        gmi_dot_circle_alt(''),
        gmi_dot_circle(''),
        gmi_minus_circle_outline(''),
        gmi_minus_circle(''),
        gmi_minus_square(''),
        gmi_minus(''),
        gmi_plus_circle_o_duplicate(''),
        gmi_plus_circle_o(''),
        gmi_plus_circle(''),
        gmi_plus_square(''),
        gmi_plus(''),
        gmi_square_o(''),
        gmi_star_circle(''),
        gmi_star_half(''),
        gmi_star_outline(''),
        gmi_star(''),
        gmi_bluetooth_connected(''),
        gmi_bluetooth_off(''),
        gmi_bluetooth_search(''),
        gmi_bluetooth_setting(''),
        gmi_bluetooth(''),
        gmi_camera_add(''),
        gmi_camera_alt(''),
        gmi_camera_bw(''),
        gmi_camera_front(''),
        gmi_camera_mic(''),
        gmi_camera_party_mode(''),
        gmi_camera_rear(''),
        gmi_camera_roll(''),
        gmi_camera_switch(''),
        gmi_camera(''),
        gmi_card_alert(''),
        gmi_card_off(''),
        gmi_card_sd(''),
        gmi_card_sim(''),
        gmi_desktop_mac(''),
        gmi_desktop_windows(''),
        gmi_device_hub(''),
        gmi_devices_off(''),
        gmi_devices(''),
        gmi_dock(''),
        gmi_floppy(''),
        gmi_gamepad(''),
        gmi_gps_dot(''),
        gmi_gps_off(''),
        gmi_gps(''),
        gmi_headset_mic(''),
        gmi_headset(''),
        gmi_input_antenna(''),
        gmi_input_composite(''),
        gmi_input_hdmi(''),
        gmi_input_power(''),
        gmi_input_svideo(''),
        gmi_keyboard_hide(''),
        gmi_keyboard(''),
        gmi_laptop_chromebook(''),
        gmi_laptop_mac(''),
        gmi_laptop(''),
        gmi_mic_off(''),
        gmi_mic_outline(''),
        gmi_mic_setting(''),
        gmi_mic(''),
        gmi_mouse(''),
        gmi_network_alert(''),
        gmi_network_locked(''),
        gmi_network_off(''),
        gmi_network_outline(''),
        gmi_network_setting(''),
        gmi_network(''),
        gmi_phone_bluetooth(''),
        gmi_phone_end(''),
        gmi_phone_forwarded(''),
        gmi_phone_in_talk(''),
        gmi_phone_locked(''),
        gmi_phone_missed(''),
        gmi_phone_msg(''),
        gmi_phone_paused(''),
        gmi_phone_ring(''),
        gmi_phone_setting(''),
        gmi_phone_sip(''),
        gmi_phone(''),
        gmi_portable_wifi_changes(''),
        gmi_portable_wifi_off(''),
        gmi_portable_wifi(''),
        gmi_radio(''),
        gmi_reader(''),
        gmi_remote_control_alt(''),
        gmi_remote_control(''),
        gmi_router(''),
        gmi_scanner(''),
        gmi_smartphone_android(''),
        gmi_smartphone_download(''),
        gmi_smartphone_erase(''),
        gmi_smartphone_info(''),
        gmi_smartphone_iphone(''),
        gmi_smartphone_landscape_lock(''),
        gmi_smartphone_landscape(''),
        gmi_smartphone_lock(''),
        gmi_smartphone_portrait_lock(''),
        gmi_smartphone_ring(''),
        gmi_smartphone_setting(''),
        gmi_smartphone_setup(''),
        gmi_smartphone(''),
        gmi_speaker(''),
        gmi_tablet_android(''),
        gmi_tablet_mac(''),
        gmi_tablet(''),
        gmi_tv_alt_play(''),
        gmi_tv_list(''),
        gmi_tv_play(''),
        gmi_tv(''),
        gmi_usb(''),
        gmi_videocam_off(''),
        gmi_videocam_switch(''),
        gmi_videocam(''),
        gmi_watch(''),
        gmi_wifi_alt_2(''),
        gmi_wifi_alt(''),
        gmi_wifi_info(''),
        gmi_wifi_lock(''),
        gmi_wifi_off(''),
        gmi_wifi_outline(''),
        gmi_wifi(''),
        gmi_arrow_left_bottom(''),
        gmi_arrow_left(''),
        gmi_arrow_merge(''),
        gmi_arrow_missed(''),
        gmi_arrow_right_top(''),
        gmi_arrow_right(''),
        gmi_arrow_split(''),
        gmi_arrows(''),
        gmi_caret_down_circle(''),
        gmi_caret_down(''),
        gmi_caret_left_circle(''),
        gmi_caret_left(''),
        gmi_caret_right_circle(''),
        gmi_caret_right(''),
        gmi_caret_up_circle(''),
        gmi_caret_up(''),
        gmi_chevron_down(''),
        gmi_chevron_left(''),
        gmi_chevron_right(''),
        gmi_chevron_up(''),
        gmi_forward(''),
        gmi_long_arrow_down(''),
        gmi_long_arrow_left(''),
        gmi_long_arrow_return(''),
        gmi_long_arrow_right(''),
        gmi_long_arrow_tab(''),
        gmi_long_arrow_up(''),
        gmi_rotate_ccw(''),
        gmi_rotate_cw(''),
        gmi_rotate_left(''),
        gmi_rotate_right(''),
        gmi_square_down(''),
        gmi_square_right(''),
        gmi_swap_alt(''),
        gmi_swap_vertical_circle(''),
        gmi_swap_vertical(''),
        gmi_swap(''),
        gmi_trending_down(''),
        gmi_trending_flat(''),
        gmi_trending_up(''),
        gmi_unfold_less(''),
        gmi_unfold_more(''),
        gmi_apps(''),
        gmi_grid_off(''),
        gmi_grid(''),
        gmi_view_agenda(''),
        gmi_view_array(''),
        gmi_view_carousel(''),
        gmi_view_column(''),
        gmi_view_comfy(''),
        gmi_view_compact(''),
        gmi_view_dashboard(''),
        gmi_view_day(''),
        gmi_view_headline(''),
        gmi_view_list_alt(''),
        gmi_view_list(''),
        gmi_view_module(''),
        gmi_view_quilt(''),
        gmi_view_stream(''),
        gmi_view_subtitles(''),
        gmi_view_toc(''),
        gmi_view_web(''),
        gmi_view_week(''),
        gmi_widgets(''),
        gmi_alarm_check(''),
        gmi_alarm_off(''),
        gmi_alarm_plus(''),
        gmi_alarm_snooze(''),
        gmi_alarm(''),
        gmi_calendar_alt(''),
        gmi_calendar_check(''),
        gmi_calendar_close(''),
        gmi_calendar_note(''),
        gmi_calendar(''),
        gmi_time_countdown(''),
        gmi_time_interval(''),
        gmi_time_restore_setting(''),
        gmi_time_restore(''),
        gmi_time(''),
        gmi_timer_off(''),
        gmi_timer(''),
        gmi_android_alt(''),
        gmi_android(''),
        gmi_apple(''),
        gmi_behance(''),
        gmi_codepen(''),
        gmi_dribbble(''),
        gmi_dropbox(''),
        gmi_evernote(''),
        gmi_facebook_box(''),
        gmi_facebook(''),
        gmi_github_box(''),
        gmi_github(''),
        gmi_google_drive(''),
        gmi_google_earth(''),
        gmi_google_glass(''),
        gmi_google_maps(''),
        gmi_google_pages(''),
        gmi_google_play(''),
        gmi_google_plus_box(''),
        gmi_google_plus(''),
        gmi_google(''),
        gmi_instagram(''),
        gmi_language_css3(''),
        gmi_language_html5(''),
        gmi_language_javascript(''),
        gmi_language_python_alt(''),
        gmi_language_python(''),
        gmi_lastfm(''),
        gmi_linkedin_box(''),
        gmi_paypal(''),
        gmi_pinterest_box(''),
        gmi_pocket(''),
        gmi_polymer(''),
        gmi_share(''),
        gmi_stackoverflow(''),
        gmi_steam_square(''),
        gmi_steam(''),
        gmi_twitter_box(''),
        gmi_twitter(''),
        gmi_vk(''),
        gmi_wikipedia(''),
        gmi_windows(''),
        gmi_aspect_ratio_alt(''),
        gmi_aspect_ratio(''),
        gmi_blur_circular(''),
        gmi_blur_linear(''),
        gmi_blur_off(''),
        gmi_blur(''),
        gmi_brightness_2(''),
        gmi_brightness_3(''),
        gmi_brightness_4(''),
        gmi_brightness_5(''),
        gmi_brightness_6(''),
        gmi_brightness_7(''),
        gmi_brightness_auto(''),
        gmi_brightness_setting(''),
        gmi_broken_image(''),
        gmi_center_focus_strong(''),
        gmi_center_focus_weak(''),
        gmi_compare(''),
        gmi_crop_16_9(''),
        gmi_crop_3_2(''),
        gmi_crop_5_4(''),
        gmi_crop_7_5(''),
        gmi_crop_din(''),
        gmi_crop_free(''),
        gmi_crop_landscape(''),
        gmi_crop_portrait(''),
        gmi_crop_square(''),
        gmi_exposure_alt(''),
        gmi_exposure(''),
        gmi_filter_b_and_w(''),
        gmi_filter_center_focus(''),
        gmi_filter_frames(''),
        gmi_filter_tilt_shift(''),
        gmi_gradient(''),
        gmi_grain(''),
        gmi_graphic_eq(''),
        gmi_hdr_off(''),
        gmi_hdr_strong(''),
        gmi_hdr_weak(''),
        gmi_hdr(''),
        gmi_iridescent(''),
        gmi_leak_off(''),
        gmi_leak(''),
        gmi_looks(''),
        gmi_loupe(''),
        gmi_panorama_horizontal(''),
        gmi_panorama_vertical(''),
        gmi_panorama_wide_angle(''),
        gmi_photo_size_select_large(''),
        gmi_photo_size_select_small(''),
        gmi_picture_in_picture(''),
        gmi_slideshow(''),
        gmi_texture(''),
        gmi_tonality(''),
        gmi_vignette(''),
        gmi_wb_auto(''),
        gmi_eject_alt(''),
        gmi_eject(''),
        gmi_equalizer(''),
        gmi_fast_forward(''),
        gmi_fast_rewind(''),
        gmi_forward_10(''),
        gmi_forward_30(''),
        gmi_forward_5(''),
        gmi_hearing(''),
        gmi_pause_circle_outline(''),
        gmi_pause_circle(''),
        gmi_pause(''),
        gmi_play_circle_outline(''),
        gmi_play_circle(''),
        gmi_play(''),
        gmi_playlist_audio(''),
        gmi_playlist_plus(''),
        gmi_repeat_one(''),
        gmi_repeat(''),
        gmi_replay_10(''),
        gmi_replay_30(''),
        gmi_replay_5(''),
        gmi_replay(''),
        gmi_shuffle(''),
        gmi_skip_next(''),
        gmi_skip_previous(''),
        gmi_stop(''),
        gmi_surround_sound(''),
        gmi_tune(''),
        gmi_volume_down(''),
        gmi_volume_mute(''),
        gmi_volume_off(''),
        gmi_volume_up(''),
        gmi_n_1_square(''),
        gmi_n_2_square(''),
        gmi_n_3_square(''),
        gmi_n_4_square(''),
        gmi_n_5_square(''),
        gmi_n_6_square(''),
        gmi_neg_1(''),
        gmi_neg_2(''),
        gmi_plus_1(''),
        gmi_plus_2(''),
        gmi_sec_10(''),
        gmi_sec_3(''),
        gmi_zero(''),
        gmi_airline_seat_flat_angled(''),
        gmi_airline_seat_flat(''),
        gmi_airline_seat_individual_suite(''),
        gmi_airline_seat_legroom_extra(''),
        gmi_airline_seat_legroom_normal(''),
        gmi_airline_seat_legroom_reduced(''),
        gmi_airline_seat_recline_extra(''),
        gmi_airline_seat_recline_normal(''),
        gmi_airplay(''),
        gmi_closed_caption(''),
        gmi_confirmation_number(''),
        gmi_developer_board(''),
        gmi_disc_full(''),
        gmi_explicit(''),
        gmi_flight_land(''),
        gmi_flight_takeoff(''),
        gmi_flip_to_back(''),
        gmi_flip_to_front(''),
        gmi_group_work(''),
        gmi_hd(''),
        gmi_hq(''),
        gmi_markunread_mailbox(''),
        gmi_memory(''),
        gmi_nfc(''),
        gmi_play_for_work(''),
        gmi_power_input(''),
        gmi_present_to_all(''),
        gmi_satellite(''),
        gmi_tap_and_play(''),
        gmi_vibration(''),
        gmi_voicemail(''),
        gmi_group(''),
        gmi_rss(''),
        gmi_shape(''),
        gmi_spinner(''),
        gmi_ungroup(''),
        gmi_500px(''),
        gmi_8tracks(''),
        gmi_amazon(''),
        gmi_blogger(''),
        gmi_delicious(''),
        gmi_disqus(''),
        gmi_flattr(''),
        gmi_flickr(''),
        gmi_github_alt(''),
        gmi_google_old(''),
        gmi_linkedin(''),
        gmi_odnoklassniki(''),
        gmi_outlook(''),
        gmi_paypal_alt(''),
        gmi_pinterest(''),
        gmi_playstation(''),
        gmi_reddit(''),
        gmi_skype(''),
        gmi_slideshare(''),
        gmi_soundcloud(''),
        gmi_tumblr(''),
        gmi_twitch(''),
        gmi_vimeo(''),
        gmi_whatsapp(''),
        gmi_xbox(''),
        gmi_yahoo(''),
        gmi_youtube_play(''),
        gmi_youtube(''),
        gmi_import_export(''),
        gmi_swap_vertical_(''),
        gmi_airplanemode_inactive(''),
        gmi_airplanemode_active(''),
        gmi_rate_review(''),
        gmi_comment_sign(''),
        gmi_network_warning(''),
        gmi_shopping_cart_add(''),
        gmi_file_add(''),
        gmi_network_wifi_scan(''),
        gmi_collection_add(''),
        gmi_format_playlist_add(''),
        gmi_format_queue_music(''),
        gmi_plus_box(''),
        gmi_tag_backspace(''),
        gmi_alarm_add(''),
        gmi_battery_charging(''),
        gmi_daydream_setting(''),
        gmi_more_horiz(''),
        gmi_book_photo(''),
        gmi_incandescent(''),
        gmi_wb_iridescent(''),
        gmi_calendar_remove(''),
        gmi_refresh_sync_disabled(''),
        gmi_refresh_sync_problem(''),
        gmi_crop_original(''),
        gmi_power_off(''),
        gmi_power_off_setting(''),
        gmi_leak_remove(''),
        gmi_star_border(''),
        gmi_brightness_low(''),
        gmi_brightness_medium(''),
        gmi_brightness_high(''),
        gmi_smartphone_portrait(''),
        gmi_live_tv(''),
        gmi_format_textdirection_l_to_r(''),
        gmi_format_textdirection_r_to_l(''),
        gmi_arrow_back(''),
        gmi_arrow_forward(''),
        gmi_arrow_in(''),
        gmi_arrow_out(''),
        gmi_rotate_90_degrees_ccw(''),
        gmi_adb(''),
        gmi_network_wifi(''),
        gmi_network_wifi_alt(''),
        gmi_network_wifi_lock(''),
        gmi_network_wifi_off(''),
        gmi_network_wifi_outline(''),
        gmi_network_wifi_info(''),
        gmi_layers_clear(''),
        gmi_colorize(''),
        gmi_format_paint(''),
        gmi_format_quote(''),
        gmi_camera_monochrome_photos(''),
        gmi_sort_by_alpha(''),
        gmi_folder_shared(''),
        gmi_folder_special(''),
        gmi_comment_dots(''),
        gmi_reorder(''),
        gmi_dehaze(''),
        gmi_sort(''),
        gmi_pages(''),
        gmi_stack_overflow(''),
        gmi_calendar_account(''),
        gmi_paste(''),
        gmi_cut(''),
        gmi_save(''),
        gmi_smartphone_code(''),
        gmi_directions_bike(''),
        gmi_directions_boat(''),
        gmi_directions_bus(''),
        gmi_directions_car(''),
        gmi_directions_railway(''),
        gmi_directions_run(''),
        gmi_directions_subway(''),
        gmi_directions_walk(''),
        gmi_local_hotel(''),
        gmi_local_activity(''),
        gmi_local_play(''),
        gmi_local_airport(''),
        gmi_local_atm(''),
        gmi_local_bar(''),
        gmi_local_cafe(''),
        gmi_local_car_wash(''),
        gmi_local_convenience_store(''),
        gmi_local_dining(''),
        gmi_local_drink(''),
        gmi_local_florist(''),
        gmi_local_gas_station(''),
        gmi_local_grocery_store(''),
        gmi_local_hospital(''),
        gmi_local_laundry_service(''),
        gmi_local_library(''),
        gmi_local_mall(''),
        gmi_local_movies(''),
        gmi_local_offer(''),
        gmi_local_parking(''),
        gmi_local_pharmacy(''),
        gmi_local_phone(''),
        gmi_local_pizza(''),
        gmi_local_post_office(''),
        gmi_local_printshop(''),
        gmi_local_see(''),
        gmi_local_shipping(''),
        gmi_local_store(''),
        gmi_local_taxi(''),
        gmi_local_wc(''),
        gmi_my_location(''),
        gmi_directions('');
        
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
                typeface = new MaterialDesignIconic();
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
        return "gmi";
    }

    public String getFontName() {
        return "Material Design Iconic";
    }

    public String getVersion() {
        return "2.2.0";
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
        return "Google. TTF created by Sergey Kupletsky";
    }

    public String getUrl() {
        return "http://zavoloklom.github.io/material-design-iconic-font/";
    }

    public String getDescription() {
        return "Material Design Iconic Font is a full suite of material design icons (created and maintained by Google) for easy scalable vector graphics on websites.";
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
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/material-design-iconic-font-v2.2.0.ttf");
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }
}