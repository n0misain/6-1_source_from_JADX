package com.mikepenz.google_material_typeface_library;

import android.content.Context;
import android.graphics.Typeface;
import com.mikepenz.iconics.typeface.IIcon;
import com.mikepenz.iconics.typeface.ITypeface;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

public class GoogleMaterial implements ITypeface {
    private static final String TTF_FILE = "google-material-font-v3.0.1.0.original.ttf";
    private static HashMap<String, Character> mChars;
    private static Typeface typeface = null;

    public enum Icon implements IIcon {
        gmd_3d_rotation(''),
        gmd_ac_unit(''),
        gmd_access_alarm(''),
        gmd_access_alarms(''),
        gmd_access_time(''),
        gmd_accessibility(''),
        gmd_accessible(''),
        gmd_account_balance(''),
        gmd_account_balance_wallet(''),
        gmd_account_box(''),
        gmd_account_circle(''),
        gmd_adb(''),
        gmd_add(''),
        gmd_add_a_photo(''),
        gmd_add_alarm(''),
        gmd_add_alert(''),
        gmd_add_box(''),
        gmd_add_circle(''),
        gmd_add_circle_outline(''),
        gmd_add_location(''),
        gmd_add_shopping_cart(''),
        gmd_add_to_photos(''),
        gmd_add_to_queue(''),
        gmd_adjust(''),
        gmd_airline_seat_flat(''),
        gmd_airline_seat_flat_angled(''),
        gmd_airline_seat_individual_suite(''),
        gmd_airline_seat_legroom_extra(''),
        gmd_airline_seat_legroom_normal(''),
        gmd_airline_seat_legroom_reduced(''),
        gmd_airline_seat_recline_extra(''),
        gmd_airline_seat_recline_normal(''),
        gmd_airplanemode_active(''),
        gmd_airplanemode_inactive(''),
        gmd_airplay(''),
        gmd_airport_shuttle(''),
        gmd_alarm(''),
        gmd_alarm_add(''),
        gmd_alarm_off(''),
        gmd_alarm_on(''),
        gmd_album(''),
        gmd_all_inclusive(''),
        gmd_all_out(''),
        gmd_android(''),
        gmd_announcement(''),
        gmd_apps(''),
        gmd_archive(''),
        gmd_arrow_back(''),
        gmd_arrow_downward(''),
        gmd_arrow_drop_down(''),
        gmd_arrow_drop_down_circle(''),
        gmd_arrow_drop_up(''),
        gmd_arrow_forward(''),
        gmd_arrow_upward(''),
        gmd_art_track(''),
        gmd_aspect_ratio(''),
        gmd_assessment(''),
        gmd_assignment(''),
        gmd_assignment_ind(''),
        gmd_assignment_late(''),
        gmd_assignment_return(''),
        gmd_assignment_returned(''),
        gmd_assignment_turned_in(''),
        gmd_assistant(''),
        gmd_assistant_photo(''),
        gmd_attach_file(''),
        gmd_attach_money(''),
        gmd_attachment(''),
        gmd_audiotrack(''),
        gmd_autorenew(''),
        gmd_av_timer(''),
        gmd_backspace(''),
        gmd_backup(''),
        gmd_battery_alert(''),
        gmd_battery_charging_full(''),
        gmd_battery_full(''),
        gmd_battery_std(''),
        gmd_battery_unknown(''),
        gmd_beach_access(''),
        gmd_beenhere(''),
        gmd_block(''),
        gmd_bluetooth(''),
        gmd_bluetooth_audio(''),
        gmd_bluetooth_connected(''),
        gmd_bluetooth_disabled(''),
        gmd_bluetooth_searching(''),
        gmd_blur_circular(''),
        gmd_blur_linear(''),
        gmd_blur_off(''),
        gmd_blur_on(''),
        gmd_book(''),
        gmd_bookmark(''),
        gmd_bookmark_border(''),
        gmd_border_all(''),
        gmd_border_bottom(''),
        gmd_border_clear(''),
        gmd_border_color(''),
        gmd_border_horizontal(''),
        gmd_border_inner(''),
        gmd_border_left(''),
        gmd_border_outer(''),
        gmd_border_right(''),
        gmd_border_style(''),
        gmd_border_top(''),
        gmd_border_vertical(''),
        gmd_branding_watermark(''),
        gmd_brightness_1(''),
        gmd_brightness_2(''),
        gmd_brightness_3(''),
        gmd_brightness_4(''),
        gmd_brightness_5(''),
        gmd_brightness_6(''),
        gmd_brightness_7(''),
        gmd_brightness_auto(''),
        gmd_brightness_high(''),
        gmd_brightness_low(''),
        gmd_brightness_medium(''),
        gmd_broken_image(''),
        gmd_brush(''),
        gmd_bubble_chart(''),
        gmd_bug_report(''),
        gmd_build(''),
        gmd_burst_mode(''),
        gmd_business(''),
        gmd_business_center(''),
        gmd_cached(''),
        gmd_cake(''),
        gmd_call(''),
        gmd_call_end(''),
        gmd_call_made(''),
        gmd_call_merge(''),
        gmd_call_missed(''),
        gmd_call_missed_outgoing(''),
        gmd_call_received(''),
        gmd_call_split(''),
        gmd_call_to_action(''),
        gmd_camera(''),
        gmd_camera_alt(''),
        gmd_camera_enhance(''),
        gmd_camera_front(''),
        gmd_camera_rear(''),
        gmd_camera_roll(''),
        gmd_cancel(''),
        gmd_card_giftcard(''),
        gmd_card_membership(''),
        gmd_card_travel(''),
        gmd_casino(''),
        gmd_cast(''),
        gmd_cast_connected(''),
        gmd_center_focus_strong(''),
        gmd_center_focus_weak(''),
        gmd_change_history(''),
        gmd_chat(''),
        gmd_chat_bubble(''),
        gmd_chat_bubble_outline(''),
        gmd_check(''),
        gmd_check_box(''),
        gmd_check_box_outline_blank(''),
        gmd_check_circle(''),
        gmd_chevron_left(''),
        gmd_chevron_right(''),
        gmd_child_care(''),
        gmd_child_friendly(''),
        gmd_chrome_reader_mode(''),
        gmd_class(''),
        gmd_clear(''),
        gmd_clear_all(''),
        gmd_close(''),
        gmd_closed_caption(''),
        gmd_cloud(''),
        gmd_cloud_circle(''),
        gmd_cloud_done(''),
        gmd_cloud_download(''),
        gmd_cloud_off(''),
        gmd_cloud_queue(''),
        gmd_cloud_upload(''),
        gmd_code(''),
        gmd_collections(''),
        gmd_collections_bookmark(''),
        gmd_color_lens(''),
        gmd_colorize(''),
        gmd_comment(''),
        gmd_compare(''),
        gmd_compare_arrows(''),
        gmd_computer(''),
        gmd_confirmation_number(''),
        gmd_contact_mail(''),
        gmd_contact_phone(''),
        gmd_contacts(''),
        gmd_content_copy(''),
        gmd_content_cut(''),
        gmd_content_paste(''),
        gmd_control_point(''),
        gmd_control_point_duplicate(''),
        gmd_copyright(''),
        gmd_create(''),
        gmd_create_new_folder(''),
        gmd_credit_card(''),
        gmd_crop(''),
        gmd_crop_16_9(''),
        gmd_crop_3_2(''),
        gmd_crop_5_4(''),
        gmd_crop_7_5(''),
        gmd_crop_din(''),
        gmd_crop_free(''),
        gmd_crop_landscape(''),
        gmd_crop_original(''),
        gmd_crop_portrait(''),
        gmd_crop_rotate(''),
        gmd_crop_square(''),
        gmd_dashboard(''),
        gmd_data_usage(''),
        gmd_date_range(''),
        gmd_dehaze(''),
        gmd_delete(''),
        gmd_delete_forever(''),
        gmd_delete_sweep(''),
        gmd_description(''),
        gmd_desktop_mac(''),
        gmd_desktop_windows(''),
        gmd_details(''),
        gmd_developer_board(''),
        gmd_developer_mode(''),
        gmd_device_hub(''),
        gmd_devices(''),
        gmd_devices_other(''),
        gmd_dialer_sip(''),
        gmd_dialpad(''),
        gmd_directions(''),
        gmd_directions_bike(''),
        gmd_directions_boat(''),
        gmd_directions_bus(''),
        gmd_directions_car(''),
        gmd_directions_railway(''),
        gmd_directions_run(''),
        gmd_directions_subway(''),
        gmd_directions_transit(''),
        gmd_directions_walk(''),
        gmd_disc_full(''),
        gmd_dns(''),
        gmd_do_not_disturb(''),
        gmd_do_not_disturb_alt(''),
        gmd_do_not_disturb_off(''),
        gmd_do_not_disturb_on(''),
        gmd_dock(''),
        gmd_domain(''),
        gmd_done(''),
        gmd_done_all(''),
        gmd_donut_large(''),
        gmd_donut_small(''),
        gmd_drafts(''),
        gmd_drag_handle(''),
        gmd_drive_eta(''),
        gmd_dvr(''),
        gmd_edit(''),
        gmd_edit_location(''),
        gmd_eject(''),
        gmd_email(''),
        gmd_enhanced_encryption(''),
        gmd_equalizer(''),
        gmd_error(''),
        gmd_error_outline(''),
        gmd_euro_symbol(''),
        gmd_ev_station(''),
        gmd_event(''),
        gmd_event_available(''),
        gmd_event_busy(''),
        gmd_event_note(''),
        gmd_event_seat(''),
        gmd_exit_to_app(''),
        gmd_expand_less(''),
        gmd_expand_more(''),
        gmd_explicit(''),
        gmd_explore(''),
        gmd_exposure(''),
        gmd_exposure_neg_1(''),
        gmd_exposure_neg_2(''),
        gmd_exposure_plus_1(''),
        gmd_exposure_plus_2(''),
        gmd_exposure_zero(''),
        gmd_extension(''),
        gmd_face(''),
        gmd_fast_forward(''),
        gmd_fast_rewind(''),
        gmd_favorite(''),
        gmd_favorite_border(''),
        gmd_featured_play_list(''),
        gmd_featured_video(''),
        gmd_feedback(''),
        gmd_fiber_dvr(''),
        gmd_fiber_manual_record(''),
        gmd_fiber_new(''),
        gmd_fiber_pin(''),
        gmd_fiber_smart_record(''),
        gmd_file_download(''),
        gmd_file_upload(''),
        gmd_filter(''),
        gmd_filter_1(''),
        gmd_filter_2(''),
        gmd_filter_3(''),
        gmd_filter_4(''),
        gmd_filter_5(''),
        gmd_filter_6(''),
        gmd_filter_7(''),
        gmd_filter_8(''),
        gmd_filter_9(''),
        gmd_filter_9_plus(''),
        gmd_filter_b_and_w(''),
        gmd_filter_center_focus(''),
        gmd_filter_drama(''),
        gmd_filter_frames(''),
        gmd_filter_hdr(''),
        gmd_filter_list(''),
        gmd_filter_none(''),
        gmd_filter_tilt_shift(''),
        gmd_filter_vintage(''),
        gmd_find_in_page(''),
        gmd_find_replace(''),
        gmd_fingerprint(''),
        gmd_first_page(''),
        gmd_fitness_center(''),
        gmd_flag(''),
        gmd_flare(''),
        gmd_flash_auto(''),
        gmd_flash_off(''),
        gmd_flash_on(''),
        gmd_flight(''),
        gmd_flight_land(''),
        gmd_flight_takeoff(''),
        gmd_flip(''),
        gmd_flip_to_back(''),
        gmd_flip_to_front(''),
        gmd_folder(''),
        gmd_folder_open(''),
        gmd_folder_shared(''),
        gmd_folder_special(''),
        gmd_font_download(''),
        gmd_format_align_center(''),
        gmd_format_align_justify(''),
        gmd_format_align_left(''),
        gmd_format_align_right(''),
        gmd_format_bold(''),
        gmd_format_clear(''),
        gmd_format_color_fill(''),
        gmd_format_color_reset(''),
        gmd_format_color_text(''),
        gmd_format_indent_decrease(''),
        gmd_format_indent_increase(''),
        gmd_format_italic(''),
        gmd_format_line_spacing(''),
        gmd_format_list_bulleted(''),
        gmd_format_list_numbered(''),
        gmd_format_paint(''),
        gmd_format_quote(''),
        gmd_format_shapes(''),
        gmd_format_size(''),
        gmd_format_strikethrough(''),
        gmd_format_textdirection_l_to_r(''),
        gmd_format_textdirection_r_to_l(''),
        gmd_format_underlined(''),
        gmd_forum(''),
        gmd_forward(''),
        gmd_forward_10(''),
        gmd_forward_30(''),
        gmd_forward_5(''),
        gmd_free_breakfast(''),
        gmd_fullscreen(''),
        gmd_fullscreen_exit(''),
        gmd_functions(''),
        gmd_g_translate(''),
        gmd_gamepad(''),
        gmd_games(''),
        gmd_gavel(''),
        gmd_gesture(''),
        gmd_get_app(''),
        gmd_gif(''),
        gmd_golf_course(''),
        gmd_gps_fixed(''),
        gmd_gps_not_fixed(''),
        gmd_gps_off(''),
        gmd_grade(''),
        gmd_gradient(''),
        gmd_grain(''),
        gmd_graphic_eq(''),
        gmd_grid_off(''),
        gmd_grid_on(''),
        gmd_group(''),
        gmd_group_add(''),
        gmd_group_work(''),
        gmd_hd(''),
        gmd_hdr_off(''),
        gmd_hdr_on(''),
        gmd_hdr_strong(''),
        gmd_hdr_weak(''),
        gmd_headset(''),
        gmd_headset_mic(''),
        gmd_healing(''),
        gmd_hearing(''),
        gmd_help(''),
        gmd_help_outline(''),
        gmd_high_quality(''),
        gmd_highlight(''),
        gmd_highlight_off(''),
        gmd_history(''),
        gmd_home(''),
        gmd_hot_tub(''),
        gmd_hotel(''),
        gmd_hourglass_empty(''),
        gmd_hourglass_full(''),
        gmd_http(''),
        gmd_https(''),
        gmd_image(''),
        gmd_image_aspect_ratio(''),
        gmd_import_contacts(''),
        gmd_import_export(''),
        gmd_important_devices(''),
        gmd_inbox(''),
        gmd_indeterminate_check_box(''),
        gmd_info(''),
        gmd_info_outline(''),
        gmd_input(''),
        gmd_insert_chart(''),
        gmd_insert_comment(''),
        gmd_insert_drive_file(''),
        gmd_insert_emoticon(''),
        gmd_insert_invitation(''),
        gmd_insert_link(''),
        gmd_insert_photo(''),
        gmd_invert_colors(''),
        gmd_invert_colors_off(''),
        gmd_iso(''),
        gmd_keyboard(''),
        gmd_keyboard_arrow_down(''),
        gmd_keyboard_arrow_left(''),
        gmd_keyboard_arrow_right(''),
        gmd_keyboard_arrow_up(''),
        gmd_keyboard_backspace(''),
        gmd_keyboard_capslock(''),
        gmd_keyboard_hide(''),
        gmd_keyboard_return(''),
        gmd_keyboard_tab(''),
        gmd_keyboard_voice(''),
        gmd_kitchen(''),
        gmd_label(''),
        gmd_label_outline(''),
        gmd_landscape(''),
        gmd_language(''),
        gmd_laptop(''),
        gmd_laptop_chromebook(''),
        gmd_laptop_mac(''),
        gmd_laptop_windows(''),
        gmd_last_page(''),
        gmd_launch(''),
        gmd_layers(''),
        gmd_layers_clear(''),
        gmd_leak_add(''),
        gmd_leak_remove(''),
        gmd_lens(''),
        gmd_library_add(''),
        gmd_library_books(''),
        gmd_library_music(''),
        gmd_lightbulb_outline(''),
        gmd_line_style(''),
        gmd_line_weight(''),
        gmd_linear_scale(''),
        gmd_link(''),
        gmd_linked_camera(''),
        gmd_list(''),
        gmd_live_help(''),
        gmd_live_tv(''),
        gmd_local_activity(''),
        gmd_local_airport(''),
        gmd_local_atm(''),
        gmd_local_bar(''),
        gmd_local_cafe(''),
        gmd_local_car_wash(''),
        gmd_local_convenience_store(''),
        gmd_local_dining(''),
        gmd_local_drink(''),
        gmd_local_florist(''),
        gmd_local_gas_station(''),
        gmd_local_grocery_store(''),
        gmd_local_hospital(''),
        gmd_local_hotel(''),
        gmd_local_laundry_service(''),
        gmd_local_library(''),
        gmd_local_mall(''),
        gmd_local_movies(''),
        gmd_local_offer(''),
        gmd_local_parking(''),
        gmd_local_pharmacy(''),
        gmd_local_phone(''),
        gmd_local_pizza(''),
        gmd_local_play(''),
        gmd_local_post_office(''),
        gmd_local_printshop(''),
        gmd_local_see(''),
        gmd_local_shipping(''),
        gmd_local_taxi(''),
        gmd_location_city(''),
        gmd_location_disabled(''),
        gmd_location_off(''),
        gmd_location_on(''),
        gmd_location_searching(''),
        gmd_lock(''),
        gmd_lock_open(''),
        gmd_lock_outline(''),
        gmd_looks(''),
        gmd_looks_3(''),
        gmd_looks_4(''),
        gmd_looks_5(''),
        gmd_looks_6(''),
        gmd_looks_one(''),
        gmd_looks_two(''),
        gmd_loop(''),
        gmd_loupe(''),
        gmd_low_priority(''),
        gmd_loyalty(''),
        gmd_mail(''),
        gmd_mail_outline(''),
        gmd_map(''),
        gmd_markunread(''),
        gmd_markunread_mailbox(''),
        gmd_memory(''),
        gmd_menu(''),
        gmd_merge_type(''),
        gmd_message(''),
        gmd_mic(''),
        gmd_mic_none(''),
        gmd_mic_off(''),
        gmd_mms(''),
        gmd_mode_comment(''),
        gmd_mode_edit(''),
        gmd_monetization_on(''),
        gmd_money_off(''),
        gmd_monochrome_photos(''),
        gmd_mood(''),
        gmd_mood_bad(''),
        gmd_more(''),
        gmd_more_horiz(''),
        gmd_more_vert(''),
        gmd_motorcycle(''),
        gmd_mouse(''),
        gmd_move_to_inbox(''),
        gmd_movie(''),
        gmd_movie_creation(''),
        gmd_movie_filter(''),
        gmd_multiline_chart(''),
        gmd_music_note(''),
        gmd_music_video(''),
        gmd_my_location(''),
        gmd_nature(''),
        gmd_nature_people(''),
        gmd_navigate_before(''),
        gmd_navigate_next(''),
        gmd_navigation(''),
        gmd_near_me(''),
        gmd_network_cell(''),
        gmd_network_check(''),
        gmd_network_locked(''),
        gmd_network_wifi(''),
        gmd_new_releases(''),
        gmd_next_week(''),
        gmd_nfc(''),
        gmd_no_encryption(''),
        gmd_no_sim(''),
        gmd_not_interested(''),
        gmd_note(''),
        gmd_note_add(''),
        gmd_notifications(''),
        gmd_notifications_active(''),
        gmd_notifications_none(''),
        gmd_notifications_off(''),
        gmd_notifications_paused(''),
        gmd_offline_pin(''),
        gmd_ondemand_video(''),
        gmd_opacity(''),
        gmd_open_in_browser(''),
        gmd_open_in_new(''),
        gmd_open_with(''),
        gmd_pages(''),
        gmd_pageview(''),
        gmd_palette(''),
        gmd_pan_tool(''),
        gmd_panorama(''),
        gmd_panorama_fish_eye(''),
        gmd_panorama_horizontal(''),
        gmd_panorama_vertical(''),
        gmd_panorama_wide_angle(''),
        gmd_party_mode(''),
        gmd_pause(''),
        gmd_pause_circle_filled(''),
        gmd_pause_circle_outline(''),
        gmd_payment(''),
        gmd_people(''),
        gmd_people_outline(''),
        gmd_perm_camera_mic(''),
        gmd_perm_contact_calendar(''),
        gmd_perm_data_setting(''),
        gmd_perm_device_information(''),
        gmd_perm_identity(''),
        gmd_perm_media(''),
        gmd_perm_phone_msg(''),
        gmd_perm_scan_wifi(''),
        gmd_person(''),
        gmd_person_add(''),
        gmd_person_outline(''),
        gmd_person_pin(''),
        gmd_person_pin_circle(''),
        gmd_personal_video(''),
        gmd_pets(''),
        gmd_phone(''),
        gmd_phone_android(''),
        gmd_phone_bluetooth_speaker(''),
        gmd_phone_forwarded(''),
        gmd_phone_in_talk(''),
        gmd_phone_iphone(''),
        gmd_phone_locked(''),
        gmd_phone_missed(''),
        gmd_phone_paused(''),
        gmd_phonelink(''),
        gmd_phonelink_erase(''),
        gmd_phonelink_lock(''),
        gmd_phonelink_off(''),
        gmd_phonelink_ring(''),
        gmd_phonelink_setup(''),
        gmd_photo(''),
        gmd_photo_album(''),
        gmd_photo_camera(''),
        gmd_photo_filter(''),
        gmd_photo_library(''),
        gmd_photo_size_select_actual(''),
        gmd_photo_size_select_large(''),
        gmd_photo_size_select_small(''),
        gmd_picture_as_pdf(''),
        gmd_picture_in_picture(''),
        gmd_picture_in_picture_alt(''),
        gmd_pie_chart(''),
        gmd_pie_chart_outlined(''),
        gmd_pin_drop(''),
        gmd_place(''),
        gmd_play_arrow(''),
        gmd_play_circle_filled(''),
        gmd_play_circle_outline(''),
        gmd_play_for_work(''),
        gmd_playlist_add(''),
        gmd_playlist_add_check(''),
        gmd_playlist_play(''),
        gmd_plus_one(''),
        gmd_poll(''),
        gmd_polymer(''),
        gmd_pool(''),
        gmd_portable_wifi_off(''),
        gmd_portrait(''),
        gmd_power(''),
        gmd_power_input(''),
        gmd_power_settings_new(''),
        gmd_pregnant_woman(''),
        gmd_present_to_all(''),
        gmd_print(''),
        gmd_priority_high(''),
        gmd_public(''),
        gmd_publish(''),
        gmd_query_builder(''),
        gmd_question_answer(''),
        gmd_queue(''),
        gmd_queue_music(''),
        gmd_queue_play_next(''),
        gmd_radio(''),
        gmd_radio_button_checked(''),
        gmd_radio_button_unchecked(''),
        gmd_rate_review(''),
        gmd_receipt(''),
        gmd_recent_actors(''),
        gmd_record_voice_over(''),
        gmd_redeem(''),
        gmd_redo(''),
        gmd_refresh(''),
        gmd_remove(''),
        gmd_remove_circle(''),
        gmd_remove_circle_outline(''),
        gmd_remove_from_queue(''),
        gmd_remove_red_eye(''),
        gmd_remove_shopping_cart(''),
        gmd_reorder(''),
        gmd_repeat(''),
        gmd_repeat_one(''),
        gmd_replay(''),
        gmd_replay_10(''),
        gmd_replay_30(''),
        gmd_replay_5(''),
        gmd_reply(''),
        gmd_reply_all(''),
        gmd_report(''),
        gmd_report_problem(''),
        gmd_restaurant(''),
        gmd_restaurant_menu(''),
        gmd_restore(''),
        gmd_restore_page(''),
        gmd_ring_volume(''),
        gmd_room(''),
        gmd_room_service(''),
        gmd_rotate_90_degrees_ccw(''),
        gmd_rotate_left(''),
        gmd_rotate_right(''),
        gmd_rounded_corner(''),
        gmd_router(''),
        gmd_rowing(''),
        gmd_rss_feed(''),
        gmd_rv_hookup(''),
        gmd_satellite(''),
        gmd_save(''),
        gmd_scanner(''),
        gmd_schedule(''),
        gmd_school(''),
        gmd_screen_lock_landscape(''),
        gmd_screen_lock_portrait(''),
        gmd_screen_lock_rotation(''),
        gmd_screen_rotation(''),
        gmd_screen_share(''),
        gmd_sd_card(''),
        gmd_sd_storage(''),
        gmd_search(''),
        gmd_security(''),
        gmd_select_all(''),
        gmd_send(''),
        gmd_sentiment_dissatisfied(''),
        gmd_sentiment_neutral(''),
        gmd_sentiment_satisfied(''),
        gmd_sentiment_very_dissatisfied(''),
        gmd_sentiment_very_satisfied(''),
        gmd_settings(''),
        gmd_settings_applications(''),
        gmd_settings_backup_restore(''),
        gmd_settings_bluetooth(''),
        gmd_settings_brightness(''),
        gmd_settings_cell(''),
        gmd_settings_ethernet(''),
        gmd_settings_input_antenna(''),
        gmd_settings_input_component(''),
        gmd_settings_input_composite(''),
        gmd_settings_input_hdmi(''),
        gmd_settings_input_svideo(''),
        gmd_settings_overscan(''),
        gmd_settings_phone(''),
        gmd_settings_power(''),
        gmd_settings_remote(''),
        gmd_settings_system_daydream(''),
        gmd_settings_voice(''),
        gmd_share(''),
        gmd_shop(''),
        gmd_shop_two(''),
        gmd_shopping_basket(''),
        gmd_shopping_cart(''),
        gmd_short_text(''),
        gmd_show_chart(''),
        gmd_shuffle(''),
        gmd_signal_cellular_4_bar(''),
        gmd_signal_cellular_connected_no_internet_4_bar(''),
        gmd_signal_cellular_no_sim(''),
        gmd_signal_cellular_null(''),
        gmd_signal_cellular_off(''),
        gmd_signal_wifi_4_bar(''),
        gmd_signal_wifi_4_bar_lock(''),
        gmd_signal_wifi_off(''),
        gmd_sim_card(''),
        gmd_sim_card_alert(''),
        gmd_skip_next(''),
        gmd_skip_previous(''),
        gmd_slideshow(''),
        gmd_slow_motion_video(''),
        gmd_smartphone(''),
        gmd_smoke_free(''),
        gmd_smoking_rooms(''),
        gmd_sms(''),
        gmd_sms_failed(''),
        gmd_snooze(''),
        gmd_sort(''),
        gmd_sort_by_alpha(''),
        gmd_spa(''),
        gmd_space_bar(''),
        gmd_speaker(''),
        gmd_speaker_group(''),
        gmd_speaker_notes(''),
        gmd_speaker_notes_off(''),
        gmd_speaker_phone(''),
        gmd_spellcheck(''),
        gmd_star(''),
        gmd_star_border(''),
        gmd_star_half(''),
        gmd_stars(''),
        gmd_stay_current_landscape(''),
        gmd_stay_current_portrait(''),
        gmd_stay_primary_landscape(''),
        gmd_stay_primary_portrait(''),
        gmd_stop(''),
        gmd_stop_screen_share(''),
        gmd_storage(''),
        gmd_store(''),
        gmd_store_mall_directory(''),
        gmd_straighten(''),
        gmd_streetview(''),
        gmd_strikethrough_s(''),
        gmd_style(''),
        gmd_subdirectory_arrow_left(''),
        gmd_subdirectory_arrow_right(''),
        gmd_subject(''),
        gmd_subscriptions(''),
        gmd_subtitles(''),
        gmd_subway(''),
        gmd_supervisor_account(''),
        gmd_surround_sound(''),
        gmd_swap_calls(''),
        gmd_swap_horiz(''),
        gmd_swap_vert(''),
        gmd_swap_vertical_circle(''),
        gmd_switch_camera(''),
        gmd_switch_video(''),
        gmd_sync(''),
        gmd_sync_disabled(''),
        gmd_sync_problem(''),
        gmd_system_update(''),
        gmd_system_update_alt(''),
        gmd_tab(''),
        gmd_tab_unselected(''),
        gmd_tablet(''),
        gmd_tablet_android(''),
        gmd_tablet_mac(''),
        gmd_tag_faces(''),
        gmd_tap_and_play(''),
        gmd_terrain(''),
        gmd_text_fields(''),
        gmd_text_format(''),
        gmd_textsms(''),
        gmd_texture(''),
        gmd_theaters(''),
        gmd_thumb_down(''),
        gmd_thumb_up(''),
        gmd_thumbs_up_down(''),
        gmd_time_to_leave(''),
        gmd_timelapse(''),
        gmd_timeline(''),
        gmd_timer(''),
        gmd_timer_10(''),
        gmd_timer_3(''),
        gmd_timer_off(''),
        gmd_title(''),
        gmd_toc(''),
        gmd_today(''),
        gmd_toll(''),
        gmd_tonality(''),
        gmd_touch_app(''),
        gmd_toys(''),
        gmd_track_changes(''),
        gmd_traffic(''),
        gmd_train(''),
        gmd_tram(''),
        gmd_transfer_within_a_station(''),
        gmd_transform(''),
        gmd_translate(''),
        gmd_trending_down(''),
        gmd_trending_flat(''),
        gmd_trending_up(''),
        gmd_tune(''),
        gmd_turned_in(''),
        gmd_turned_in_not(''),
        gmd_tv(''),
        gmd_unarchive(''),
        gmd_undo(''),
        gmd_unfold_less(''),
        gmd_unfold_more(''),
        gmd_update(''),
        gmd_usb(''),
        gmd_verified_user(''),
        gmd_vertical_align_bottom(''),
        gmd_vertical_align_center(''),
        gmd_vertical_align_top(''),
        gmd_vibration(''),
        gmd_video_call(''),
        gmd_video_label(''),
        gmd_video_library(''),
        gmd_videocam(''),
        gmd_videocam_off(''),
        gmd_videogame_asset(''),
        gmd_view_agenda(''),
        gmd_view_array(''),
        gmd_view_carousel(''),
        gmd_view_column(''),
        gmd_view_comfy(''),
        gmd_view_compact(''),
        gmd_view_day(''),
        gmd_view_headline(''),
        gmd_view_list(''),
        gmd_view_module(''),
        gmd_view_quilt(''),
        gmd_view_stream(''),
        gmd_view_week(''),
        gmd_vignette(''),
        gmd_visibility(''),
        gmd_visibility_off(''),
        gmd_voice_chat(''),
        gmd_voicemail(''),
        gmd_volume_down(''),
        gmd_volume_mute(''),
        gmd_volume_off(''),
        gmd_volume_up(''),
        gmd_vpn_key(''),
        gmd_vpn_lock(''),
        gmd_wallpaper(''),
        gmd_warning(''),
        gmd_watch(''),
        gmd_watch_later(''),
        gmd_wb_auto(''),
        gmd_wb_cloudy(''),
        gmd_wb_incandescent(''),
        gmd_wb_iridescent(''),
        gmd_wb_sunny(''),
        gmd_wc(''),
        gmd_web(''),
        gmd_web_asset(''),
        gmd_weekend(''),
        gmd_whatshot(''),
        gmd_widgets(''),
        gmd_wifi(''),
        gmd_wifi_lock(''),
        gmd_wifi_tethering(''),
        gmd_work(''),
        gmd_wrap_text(''),
        gmd_youtube_searched_for(''),
        gmd_zoom_in(''),
        gmd_zoom_out(''),
        gmd_zoom_out_map('');
        
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
                typeface = new GoogleMaterial();
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
        return "gmd";
    }

    public String getFontName() {
        return "Google Material";
    }

    public String getVersion() {
        return "3.0.1.0.original";
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
        return "Google";
    }

    public String getUrl() {
        return "https://github.com/google/material-design-icons";
    }

    public String getDescription() {
        return "Material design icons are the official icon set from Google that are designed under the material design guidelines.";
    }

    public String getLicense() {
        return "CC-BY 4.0";
    }

    public String getLicenseUrl() {
        return "http://creativecommons.org/licenses/by/4.0/";
    }

    public Typeface getTypeface(Context context) {
        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.getAssets(), "fonts/google-material-font-v3.0.1.0.original.ttf");
            } catch (Exception e) {
                return null;
            }
        }
        return typeface;
    }
}
