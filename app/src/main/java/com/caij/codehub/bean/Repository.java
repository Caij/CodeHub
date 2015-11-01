package com.caij.codehub.bean;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

/**
 * Created by Caij on 2015/9/17.
 */
public class Repository extends Entity{

    /**
     * id : 1296269
     * owner : {"login":"octocat","id":1,"avatar_url":"https://github.com/images/error/octocat_happy.gif","gravatar_id":"","url":"https://api.github.com/users/octocat","html_url":"https://github.com/octocat","followers_url":"https://api.github.com/users/octocat/followers","following_url":"https://api.github.com/users/octocat/following{/other_user}","gists_url":"https://api.github.com/users/octocat/gists{/gist_id}","starred_url":"https://api.github.com/users/octocat/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/octocat/subscriptions","organizations_url":"https://api.github.com/users/octocat/orgs","repos_url":"https://api.github.com/users/octocat/repos","events_url":"https://api.github.com/users/octocat/events{/privacy}","received_events_url":"https://api.github.com/users/octocat/received_events","type":"User","site_admin":false}
     * name : Hello-World
     * full_name : octocat/Hello-World
     * description : This your first repo!
     * private : false
     * fork : true
     * url : https://api.github.com/repos/octocat/Hello-World
     * html_url : https://github.com/octocat/Hello-World
     * archive_url : http://api.github.com/repos/octocat/Hello-World/{archive_format}{/ref}
     * assignees_url : http://api.github.com/repos/octocat/Hello-World/assignees{/user}
     * blobs_url : http://api.github.com/repos/octocat/Hello-World/git/blobs{/sha}
     * branches_url : http://api.github.com/repos/octocat/Hello-World/branches{/branch}
     * clone_url : https://github.com/octocat/Hello-World.git
     * collaborators_url : http://api.github.com/repos/octocat/Hello-World/collaborators{/collaborator}
     * comments_url : http://api.github.com/repos/octocat/Hello-World/comments{/number}
     * commits_url : http://api.github.com/repos/octocat/Hello-World/commits{/sha}
     * compare_url : http://api.github.com/repos/octocat/Hello-World/compare/{base}...{head}
     * contents_url : http://api.github.com/repos/octocat/Hello-World/contents/{+path}
     * contributors_url : http://api.github.com/repos/octocat/Hello-World/contributors
     * downloads_url : http://api.github.com/repos/octocat/Hello-World/downloads
     * events_url : http://api.github.com/repos/octocat/Hello-World/events
     * forks_url : http://api.github.com/repos/octocat/Hello-World/forks
     * git_commits_url : http://api.github.com/repos/octocat/Hello-World/git/commits{/sha}
     * git_refs_url : http://api.github.com/repos/octocat/Hello-World/git/refs{/sha}
     * git_tags_url : http://api.github.com/repos/octocat/Hello-World/git/tags{/sha}
     * git_url : git:github.com/octocat/Hello-World.git
     * hooks_url : http://api.github.com/repos/octocat/Hello-World/hooks
     * issue_comment_url : http://api.github.com/repos/octocat/Hello-World/issues/comments{/number}
     * issue_events_url : http://api.github.com/repos/octocat/Hello-World/issues/events{/number}
     * issues_url : http://api.github.com/repos/octocat/Hello-World/issues{/number}
     * keys_url : http://api.github.com/repos/octocat/Hello-World/keys{/key_id}
     * labels_url : http://api.github.com/repos/octocat/Hello-World/labels{/name}
     * languages_url : http://api.github.com/repos/octocat/Hello-World/languages
     * merges_url : http://api.github.com/repos/octocat/Hello-World/merges
     * milestones_url : http://api.github.com/repos/octocat/Hello-World/milestones{/number}
     * mirror_url : git:git.example.com/octocat/Hello-World
     * notifications_url : http://api.github.com/repos/octocat/Hello-World/notifications{?since, all, participating}
     * pulls_url : http://api.github.com/repos/octocat/Hello-World/pulls{/number}
     * releases_url : http://api.github.com/repos/octocat/Hello-World/releases{/id}
     * ssh_url : git@github.com:octocat/Hello-World.git
     * stargazers_url : http://api.github.com/repos/octocat/Hello-World/stargazers
     * statuses_url : http://api.github.com/repos/octocat/Hello-World/statuses/{sha}
     * subscribers_url : http://api.github.com/repos/octocat/Hello-World/subscribers
     * subscription_url : http://api.github.com/repos/octocat/Hello-World/subscription
     * svn_url : https://svn.github.com/octocat/Hello-World
     * tags_url : http://api.github.com/repos/octocat/Hello-World/tags
     * teams_url : http://api.github.com/repos/octocat/Hello-World/teams
     * trees_url : http://api.github.com/repos/octocat/Hello-World/git/trees{/sha}
     * homepage : https://github.com
     * language : null
     * forks_count : 9
     * stargazers_count : 80
     * watchers_count : 80
     * size : 108
     * default_branch : master
     * open_issues_count : 0
     * has_issues : true
     * has_wiki : true
     * has_pages : false
     * has_downloads : true
     * pushed_at : 2011-01-26T19:06:43Z
     * created_at : 2011-01-26T19:01:12Z
     * updated_at : 2011-01-26T19:14:43Z
     * permissions : {"admin":false,"push":false,"pull":true}
     */

    private int id;
    private User owner;
    private String name;
    private String full_name;
    private String description;
    @SerializedName("private")
    private boolean privateX;
    private boolean fork;
    private String url;
    private String html_url;
    private String archive_url;
    private String assignees_url;
    private String blobs_url;
    private String branches_url;
    private String clone_url;
    private String collaborators_url;
    private String comments_url;
    private String commits_url;
    private String compare_url;
    private String contents_url;
    private String contributors_url;
    private String downloads_url;
    private String events_url;
    private String forks_url;
    private String git_commits_url;
    private String git_refs_url;
    private String git_tags_url;
    private String git_url;
    private String hooks_url;
    private String issue_comment_url;
    private String issue_events_url;
    private String issues_url;
    private String keys_url;
    private String labels_url;
    private String languages_url;
    private String merges_url;
    private String milestones_url;
    private String mirror_url;
    private String notifications_url;
    private String pulls_url;
    private String releases_url;
    private String ssh_url;
    private String stargazers_url;
    private String statuses_url;
    private String subscribers_url;
    private String subscription_url;
    private String svn_url;
    private String tags_url;
    private String teams_url;
    private String trees_url;
    private String homepage;
    private String language;
    private int forks_count;
    private int stargazers_count;
    private int watchers_count;
    private int size;
    private String default_branch;
    private int open_issues_count;
    private boolean has_issues;
    private boolean has_wiki;
    private boolean has_pages;
    private boolean has_downloads;
    private Date pushed_at;
    private Date created_at;
    private Date updated_at;
    private PermissionsEntity permissions;

    public void setId(int id) {
        this.id = id;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrivateX(boolean privateX) {
        this.privateX = privateX;
    }

    public void setFork(boolean fork) {
        this.fork = fork;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setArchive_url(String archive_url) {
        this.archive_url = archive_url;
    }

    public void setAssignees_url(String assignees_url) {
        this.assignees_url = assignees_url;
    }

    public void setBlobs_url(String blobs_url) {
        this.blobs_url = blobs_url;
    }

    public void setBranches_url(String branches_url) {
        this.branches_url = branches_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public void setCollaborators_url(String collaborators_url) {
        this.collaborators_url = collaborators_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public void setCompare_url(String compare_url) {
        this.compare_url = compare_url;
    }

    public void setContents_url(String contents_url) {
        this.contents_url = contents_url;
    }

    public void setContributors_url(String contributors_url) {
        this.contributors_url = contributors_url;
    }

    public void setDownloads_url(String downloads_url) {
        this.downloads_url = downloads_url;
    }

    public void setEvents_url(String events_url) {
        this.events_url = events_url;
    }

    public void setForks_url(String forks_url) {
        this.forks_url = forks_url;
    }

    public void setGit_commits_url(String git_commits_url) {
        this.git_commits_url = git_commits_url;
    }

    public void setGit_refs_url(String git_refs_url) {
        this.git_refs_url = git_refs_url;
    }

    public void setGit_tags_url(String git_tags_url) {
        this.git_tags_url = git_tags_url;
    }

    public void setGit_url(String git_url) {
        this.git_url = git_url;
    }

    public void setHooks_url(String hooks_url) {
        this.hooks_url = hooks_url;
    }

    public void setIssue_comment_url(String issue_comment_url) {
        this.issue_comment_url = issue_comment_url;
    }

    public void setIssue_events_url(String issue_events_url) {
        this.issue_events_url = issue_events_url;
    }

    public void setIssues_url(String issues_url) {
        this.issues_url = issues_url;
    }

    public void setKeys_url(String keys_url) {
        this.keys_url = keys_url;
    }

    public void setLabels_url(String labels_url) {
        this.labels_url = labels_url;
    }

    public void setLanguages_url(String languages_url) {
        this.languages_url = languages_url;
    }

    public void setMerges_url(String merges_url) {
        this.merges_url = merges_url;
    }

    public void setMilestones_url(String milestones_url) {
        this.milestones_url = milestones_url;
    }

    public void setMirror_url(String mirror_url) {
        this.mirror_url = mirror_url;
    }

    public void setNotifications_url(String notifications_url) {
        this.notifications_url = notifications_url;
    }

    public void setPulls_url(String pulls_url) {
        this.pulls_url = pulls_url;
    }

    public void setReleases_url(String releases_url) {
        this.releases_url = releases_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public void setStargazers_url(String stargazers_url) {
        this.stargazers_url = stargazers_url;
    }

    public void setStatuses_url(String statuses_url) {
        this.statuses_url = statuses_url;
    }

    public void setSubscribers_url(String subscribers_url) {
        this.subscribers_url = subscribers_url;
    }

    public void setSubscription_url(String subscription_url) {
        this.subscription_url = subscription_url;
    }

    public void setSvn_url(String svn_url) {
        this.svn_url = svn_url;
    }

    public void setTags_url(String tags_url) {
        this.tags_url = tags_url;
    }

    public void setTeams_url(String teams_url) {
        this.teams_url = teams_url;
    }

    public void setTrees_url(String trees_url) {
        this.trees_url = trees_url;
    }

    public void setHomepage(String homepage) {
        this.homepage = homepage;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setForks_count(int forks_count) {
        this.forks_count = forks_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }

    public void setWatchers_count(int watchers_count) {
        this.watchers_count = watchers_count;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setDefault_branch(String default_branch) {
        this.default_branch = default_branch;
    }

    public void setOpen_issues_count(int open_issues_count) {
        this.open_issues_count = open_issues_count;
    }

    public void setHas_issues(boolean has_issues) {
        this.has_issues = has_issues;
    }

    public void setHas_wiki(boolean has_wiki) {
        this.has_wiki = has_wiki;
    }

    public void setHas_pages(boolean has_pages) {
        this.has_pages = has_pages;
    }

    public void setHas_downloads(boolean has_downloads) {
        this.has_downloads = has_downloads;
    }

    public void setPushed_at(Date pushed_at) {
        this.pushed_at = pushed_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public void setPermissions(PermissionsEntity permissions) {
        this.permissions = permissions;
    }

    public int getId() {
        return id;
    }

    public User getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getFull_name() {
        return full_name;
    }

    public String getDescription() {
        return description;
    }

    public boolean getPrivateX() {
        return privateX;
    }

    public boolean isFork() {
        return fork;
    }

    public String getUrl() {
        return url;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getArchive_url() {
        return archive_url;
    }

    public String getAssignees_url() {
        return assignees_url;
    }

    public String getBlobs_url() {
        return blobs_url;
    }

    public String getBranches_url() {
        return branches_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public String getCollaborators_url() {
        return collaborators_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public String getCompare_url() {
        return compare_url;
    }

    public String getContents_url() {
        return contents_url;
    }

    public String getContributors_url() {
        return contributors_url;
    }

    public String getDownloads_url() {
        return downloads_url;
    }

    public String getEvents_url() {
        return events_url;
    }

    public String getForks_url() {
        return forks_url;
    }

    public String getGit_commits_url() {
        return git_commits_url;
    }

    public String getGit_refs_url() {
        return git_refs_url;
    }

    public String getGit_tags_url() {
        return git_tags_url;
    }

    public String getGit_url() {
        return git_url;
    }

    public String getHooks_url() {
        return hooks_url;
    }

    public String getIssue_comment_url() {
        return issue_comment_url;
    }

    public String getIssue_events_url() {
        return issue_events_url;
    }

    public String getIssues_url() {
        return issues_url;
    }

    public String getKeys_url() {
        return keys_url;
    }

    public String getLabels_url() {
        return labels_url;
    }

    public String getLanguages_url() {
        return languages_url;
    }

    public String getMerges_url() {
        return merges_url;
    }

    public String getMilestones_url() {
        return milestones_url;
    }

    public String getMirror_url() {
        return mirror_url;
    }

    public String getNotifications_url() {
        return notifications_url;
    }

    public String getPulls_url() {
        return pulls_url;
    }

    public String getReleases_url() {
        return releases_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public String getStargazers_url() {
        return stargazers_url;
    }

    public String getStatuses_url() {
        return statuses_url;
    }

    public String getSubscribers_url() {
        return subscribers_url;
    }

    public String getSubscription_url() {
        return subscription_url;
    }

    public String getSvn_url() {
        return svn_url;
    }

    public String getTags_url() {
        return tags_url;
    }

    public String getTeams_url() {
        return teams_url;
    }

    public String getTrees_url() {
        return trees_url;
    }

    public String getHomepage() {
        return homepage;
    }

    public String getLanguage() {
        return language;
    }

    public int getForks_count() {
        return forks_count;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public int getWatchers_count() {
        return watchers_count;
    }

    public int getSize() {
        return size;
    }

    public String getDefault_branch() {
        return default_branch;
    }

    public int getOpen_issues_count() {
        return open_issues_count;
    }

    public boolean getHas_issues() {
        return has_issues;
    }

    public boolean getHas_wiki() {
        return has_wiki;
    }

    public boolean getHas_pages() {
        return has_pages;
    }

    public boolean getHas_downloads() {
        return has_downloads;
    }

    public Date getPushed_at() {
        return pushed_at;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public PermissionsEntity getPermissions() {
        return permissions;
    }

    public static class PermissionsEntity {
        /**
         * admin : false
         * push : false
         * pull : true
         */

        private boolean admin;
        private boolean push;
        private boolean pull;

        public void setAdmin(boolean admin) {
            this.admin = admin;
        }

        public void setPush(boolean push) {
            this.push = push;
        }

        public void setPull(boolean pull) {
            this.pull = pull;
        }

        public boolean getAdmin() {
            return admin;
        }

        public boolean getPush() {
            return push;
        }

        public boolean getPull() {
            return pull;
        }
    }

    @Override
    public boolean equals(Object o) {
        Repository repository = (Repository) o;
        return repository.getId() == getId();
    }
}
