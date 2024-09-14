<?php
require_once __DIR__ . '/../inc/global.inc.php';

$this_section = SECTION_PLATFORM_ADMIN;
api_protect_admin_script(true);


$nameTools = get_lang('Administrator');
$interbreadcrumb[] = [  
    'url' => api_get_path(WEB_CODE_PATH) . 'admin/index.php',
    'name' => get_lang('PlatformAdmin'),
];
$queryString = $_SERVER['QUERY_STRING'];

parse_str($queryString, $queryArray);

function isChecked($type, $value, $queryArray) {
    return isset($queryArray[$type]) && in_array($value, $queryArray[$type]) ? 'checked' : '';
}

$url=api_get_path(WEB_CODE_PATH);
echo $url;

$user=api_get_user_info();
$user_status=$user['status'];//posibles son 1,22,3





$formHtml = '
<form action="" method="POST">
    <div class="form-group">
        <h1>Configuraciones de Roles</h1>
        <div class="col-sm-8">
            <label for="">Rol :</label>
            <select name="role" id="">
                <option value="22" ' . ($user_status == 22 ? 'selected' : '') . '>Portal Admin</option>
                <option value="3" ' . ($user_status == 3 ? 'selected' : '') . '>Administrador de Secciones</option>
                <option value="1" ' . ($user_status == 1 ? 'selected' : '') . '>Trainer</option>
            </select>
        </div>
        <div class="col-sm-8">
            <label for="">Users:</label><br>
            <input type="checkbox" name="users[]" value="user_list.php" ' . isChecked('users', 'user_list.php', $queryArray) . '> User List<br>
            <input type="checkbox" name="users[]" value="user_add.php" ' . isChecked('users', 'user_add.php', $queryArray) . '> Add a user<br>
            <input type="checkbox" name="users[]" value="user_export.php" ' . isChecked('users', 'user_export.php', $queryArray) . '> Export users list<br>
            <input type="checkbox" name="users[]" value="user_import.php" ' . isChecked('users', 'user_import.php', $queryArray) . '> Import users list<br>
            <input type="checkbox" name="users[]" value="user_update_import.php" ' . isChecked('users', 'user_update_import.php', $queryArray) . '> Edit users list<br>
            <input type="checkbox" name="users[]" value="user_anonymize_import.php" ' . isChecked('users', 'user_anonymize_import.php', $queryArray) . '> Anonymise users list<br>
            <input type="checkbox" name="users[]" value="extra_fields.php?type=user" ' . isChecked('users', 'extra_fields.php?type=user', $queryArray) . '> Profiling<br>
            <input type="checkbox" name="users[]" value="usergroups.php" ' . isChecked('users', 'usergroups.php', $queryArray) . '> Classes<br>
        </div><br>
        <div class="col">
            <label for="">Courses:</label><br>
            <input type="checkbox" name="courses[]" value="course_list.php" ' . isChecked('courses', 'course_list.php', $queryArray) . '> Course List<br>
            <input type="checkbox" name="courses[]" value="course_add.php" ' . isChecked('courses', 'course_add.php', $queryArray) . '> Create Course<br>
            <input type="checkbox" name="courses[]" value="course_export.php" ' . isChecked('courses', 'course_export.php', $queryArray) . '> Export Course<br>
            <input type="checkbox" name="courses[]" value="course_import.php" ' . isChecked('courses', 'course_import.php', $queryArray) . '> Import Course list<br>
            <input type="checkbox" name="courses[]" value="course_category.php" ' . isChecked('courses', 'course_category.php', $queryArray) . '> Courses Categories<br>
            <input type="checkbox" name="courses[]" value="subscribe_user2course.php" ' . isChecked('courses', 'subscribe_user2course.php', $queryArray) . '> Add users to course<br>
            <input type="checkbox" name="courses[]" value="course_user_import.php" ' . isChecked('courses', 'course_user_import.php', $queryArray) . '> Import users list<br>
            <input type="checkbox" name="courses[]" value="extra_fields.php?type=course" ' . isChecked('courses', 'extra_fields.php?type=course', $queryArray) . '> Manage extra fields for courses<br>
            <input type="checkbox" name="courses[]" value="questions.php" ' . isChecked('courses', 'questions.php', $queryArray) . '> Questions<br>
            <input type="checkbox" name="courses[]" value="../coursecopy/copy_course_to_other_course.php" ' . isChecked('courses', '../coursecopy/copy_course_to_other_course.php', $queryArray) . '> Copy from course to another course
        </div><br>
        <div class="col-sm-8">
            <label for="">Portal</label><br>
            <input type="checkbox" name="platform[]" value="settings.php" ' . isChecked('platform', 'settings.php', $queryArray) . '> Configuration settings<br>
            <input type="checkbox" name="platform[]" value="security_key.php" ' . isChecked('platform', 'security_key.php', $queryArray) . '> Api security Keys<br>
            <input type="checkbox" name="platform[]" value="languages.php" ' . isChecked('platform', 'languages.php', $queryArray) . '> Languajes<br>
            <input type="checkbox" name="platform[]" value="settings.php?category=Plugins" ' . isChecked('platform', 'settings.php?category=Plugins', $queryArray) . '>PLugins<br>
            <input type="checkbox" name="platform[]" value="settings.php?category=Regions" ' . isChecked('platform', 'settings.php?category=Regions', $queryArray) . '> Regions<br>
            <input type="checkbox" name="platform[]" value="system_announcements.php" ' . isChecked('platform', 'system_announcements.php', $queryArray) . '>Portal news<br>
            <input type="checkbox" name="platform[]" value=" '.$url.'calendar/agenda_js.php?type=admin" ' . isChecked('platform', ''.$url.'calendar/agenda_js.php?type=admin', $queryArray) . '> Global agenda<br>
            <input type="checkbox" name="platform[]" value=" '.$url.'admin/import_course_agenda_reminders.php" ' . isChecked('platform', ''.$url.'admin/import_course_agenda_reminders.php', $queryArray) . '>Import course events<br>
            <input type="checkbox" name="platform[]" value="configure_homepage.php" ' . isChecked('platform', 'configure_homepage.php', $queryArray) . '> Edit portal homepage<br>
            <input type="checkbox" name="platform[]" value="configure_sorting_options.php" ' . isChecked('platform', 'configure_sorting_options.php', $queryArray) . '> Configure sortings options <br>
            <input type="checkbox" name="platform[]" value="configure_banner_messages.php" ' . isChecked('platform', 'configure_banner_messages.php', $queryArray) . '> Configure banner messages<br>
            <input type="checkbox" name="platform[]" value="configure_inscription.php" ' . isChecked('platform', 'configure_inscription.php', $queryArray) . '> Setting the registration page<br>
            <input type="checkbox" name="platform[]" value="statistics/index.php" ' . isChecked('platform', 'statistics/index.php', $queryArray) . '> Statics<br>
            <input type="checkbox" name="platform[]" value=" '.$url.'mySpace/company_reports.php" ' . isChecked('platform', ''.$url.'mySpace/company_reports.php', $queryArray) . '>Reports<br>
            <input type="checkbox" name="platform[]" value=" '.$url.'admin/teacher_time_report.php" ' . isChecked('platform', ''.$url.'admin/teacher_time_report.php', $queryArray) . '>Teachers time report<br>
            <input type="checkbox" name="platform[]" value="extra_field_list.php" ' . isChecked('platform', 'extra_field_list.php', $queryArray) . '>Extra fields <br>
            <input type="checkbox" name="platform[]" value="create_template.php" ' . isChecked('platform', 'create_template.php', $queryArray) . '> Create template<br>
            <input type="checkbox" name="platform[]" value="access_urls.php" ' . isChecked('platform', 'access_urls.php', $queryArray) . '> Configure multiple access URL<br>
            <input type="checkbox" name="platform[]" value="http://localhost:8031/plugin/eskyshoppingcartcourses/src/configuration.php"' . isChecked('platform', 'http://localhost:8031/plugin/eskyshoppingcartcourses/src/configuration.php', $queryArray) . '> Course settings / Course and prices configuration<br>
            <input type="checkbox" name="platform[]"  value="http://localhost:8031/plugin/eskyshoppingcartcourses/src/sales_report.php"' . isChecked('platform', 'http://localhost:8031/plugin/eskyshoppingcartcourses/src/sales_report.php', $queryArray) . '> Sales Report<br>
        </div>
        <div class="col">
            <label for="">Course sessions</label><br>
            <input type="checkbox" name="sessions[]" value=" '.$url.'session/session_list.php" ' . isChecked('sessions', ''.$url.'session/session_list.php', $queryArray) . '> Training sessions list<br>
            <input type="checkbox" name="sessions[]" value=" '.$url.'session/session_add.php" ' . isChecked('sessions', ''.$url.'session/session_add.php', $queryArray) . '> Add a training session<br>
            <input type="checkbox" name="sessions[]" value=" '.$url.'session/session_category_list.php" ' . isChecked('sessions', ''.$url.'session/session_category_list.php', $queryArray) . '> Sessions categories list<br>
            <input type="checkbox" name="sessions[]" value=" '.$url.'session/session_import.php" ' . isChecked('sessions', ''.$url.'session/session_import.php', $queryArray) . '> Import sessions list<br>
            <input type="checkbox" name="sessions[]" value=" '.$url.'session/session_import_drh.php" ' . isChecked('sessions', ''.$url.'session/session_import_drh.php', $queryArray) . '> Import list of HR directors info sessions<br>
            <input type="checkbox" name="sessions[]" value=" '.$url.'session/session_export.php" ' . isChecked('sessions', ''.$url.'session/session_export.php', $queryArray) . '> Export sessions list<br>
            <input type="checkbox" name="sessions[]" value="../coursecopy/copy_course_session.php" ' . isChecked('sessions', '../coursecopy/copy_course_session.php', $queryArray) . '> Copy from course in session ti another  <br>
            <input type="checkbox" name="sessions[]" value="../coursecopy/move_users_from_course_to_session.php" ' . isChecked('sessions', '../coursecopy/move_users_from_course_to_session.php', $queryArray) . '> Move users results from base course ta a session<br>
            <input type="checkbox" name="sessions[]" value="career_dashboard.php" ' . isChecked('sessions', 'career_dashboard.php', $queryArray) . '> Carrers and promotions<br>
            <input type="checkbox" name="sessions[]" value="extra_fields.php?type=session" ' . isChecked('sessions', 'extra_fields.php?type=session', $queryArray) . '> Manage session fields<br>
            <input type="checkbox" name="sessions[]" value="resource_sequence.php" ' . isChecked('sessions', 'resource_sequence.php', $queryArray) . '> Resources sequencing<br>
            <input type="checkbox" name="sessions[]" value="export_exercise_results.php" ' . isChecked('sessions', 'export_exercise_results.php', $queryArray) . '> Export all results from an exercise<br>
        </div>
        <div class="col-sm-8">
            <label for="">System</label><br>
            <input type="checkbox" name="settings[]" value="archive_cleanup.php"'.isChecked('settings','archive_cleanup.php',$queryArray).'> Cleanup of cache and temporary files<br>
            <input type="checkbox" name="settings[]" value="special_exports.php"'.isChecked('settings','special_exports.php',$queryArray).'> Special exports  <br>
            <input type="checkbox" name="settings[]" value="system_status.php"'.isChecked('settings','system_status.php',$queryArray).'> System status <br>
            <input type="checkbox" name="settings[]" value=" '.$url.'ticket/tickets.php "'.isChecked('settings',''.$url.'ticket/tickets.php',$queryArray).'>Tickets <br>
        </div>
        <div class="col">
            <label for="">Skills</label><br>
            <input type="checkbox" name="skills[]" value="" '.isChecked('skills', '',  $queryArray).'> Skills wheel <br>
            <input type="checkbox" name="skills[]" value="" '.isChecked('skills', '',  $queryArray).'> Skills import <br>
            <input type="checkbox" name="skills[]" value="" '.isChecked('skills', '',  $queryArray).'> Manage skills <br>
            <input type="checkbox" name="skills[]" value="" '.isChecked('skills', '',  $queryArray).'> Manage skills levels <br>
            <input type="checkbox" name="skills[]" value="" '.isChecked('skills', '',  $queryArray).'> Skills ranking <br>
            <input type="checkbox" name="skills[]" value="" '.isChecked('skills', '',  $queryArray).'> Skills and assessments <br>

        </div>

        <input type="submit" value="Submit">
    </div>
</form>
';

$template = new Template($nameTools);
$template->assign('content', $formHtml); 
$template->display_one_col_template();
