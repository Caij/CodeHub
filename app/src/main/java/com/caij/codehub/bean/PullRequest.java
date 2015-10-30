package com.caij.codehub.bean;

/**
 * Created by Caij on 2015/10/30.
 */
public class PullRequest extends Entity{

    /**
     * url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1
     * id : 34778301
     * html_url : https://github.com/baxterthehacker/public-repo/pull/1
     * diff_url : https://github.com/baxterthehacker/public-repo/pull/1.diff
     * patch_url : https://github.com/baxterthehacker/public-repo/pull/1.patch
     * issue_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/1
     * number : 1
     * state : open
     * locked : false
     * title : Update the README with new information
     * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
     * body : This is a pretty simple change that we need to pull into master.
     * created_at : 2015-05-05T23:40:27Z
     * updated_at : 2015-05-05T23:40:27Z
     * closed_at : null
     * merged_at : null
     * merge_commit_sha : null
     * assignee : null
     * milestone : null
     * commits_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits
     * review_comments_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments
     * review_comment_url : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}
     * comments_url : https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments
     * statuses_url : https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
     * head : {"label":"baxterthehacker:changes","ref":"changes","sha":"0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"repo":{"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:26Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}}
     * base : {"label":"baxterthehacker:master","ref":"master","sha":"9049f1265b7d61be4a8904a9a27120d2064dab3b","user":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"repo":{"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:26Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}}
     * _links : {"self":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"},"html":{"href":"https://github.com/baxterthehacker/public-repo/pull/1"},"issue":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1"},"comments":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments"},"review_comments":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments"},"review_comment":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}"},"commits":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits"},"statuses":{"href":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"}}
     * merged : false
     * mergeable : null
     * mergeable_state : unknown
     * merged_by : null
     * comments : 0
     * review_comments : 0
     * commits : 1
     * additions : 1
     * deletions : 1
     * changed_files : 1
     */

    private String url;
    private int id;
    private String html_url;
    private String diff_url;
    private String patch_url;
    private String issue_url;
    private int number;
    private String state;
    private boolean locked;
    private String title;
    private String body;
    private String created_at;
    private String updated_at;
    private Object closed_at;
    private Object merged_at;
    private Object merge_commit_sha;
    private Object assignee;
    private Object milestone;
    private String commits_url;
    private String review_comments_url;
    private String review_comment_url;
    private String comments_url;
    private String statuses_url;
    private HeadEntity head;
    private BaseEntity base;
    private LinksEntity _links;
    private boolean merged;
    private Object mergeable;
    private String mergeable_state;
    private Object merged_by;
    private int comments;
    private int review_comments;
    private int commits;
    private int additions;
    private int deletions;
    private int changed_files;
    private User user;

    public void setUrl(String url) {
        this.url = url;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    public void setDiff_url(String diff_url) {
        this.diff_url = diff_url;
    }

    public void setPatch_url(String patch_url) {
        this.patch_url = patch_url;
    }

    public void setIssue_url(String issue_url) {
        this.issue_url = issue_url;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }

    public void setClosed_at(Object closed_at) {
        this.closed_at = closed_at;
    }

    public void setMerged_at(Object merged_at) {
        this.merged_at = merged_at;
    }

    public void setMerge_commit_sha(Object merge_commit_sha) {
        this.merge_commit_sha = merge_commit_sha;
    }

    public void setAssignee(Object assignee) {
        this.assignee = assignee;
    }

    public void setMilestone(Object milestone) {
        this.milestone = milestone;
    }

    public void setCommits_url(String commits_url) {
        this.commits_url = commits_url;
    }

    public void setReview_comments_url(String review_comments_url) {
        this.review_comments_url = review_comments_url;
    }

    public void setReview_comment_url(String review_comment_url) {
        this.review_comment_url = review_comment_url;
    }

    public void setComments_url(String comments_url) {
        this.comments_url = comments_url;
    }

    public void setStatuses_url(String statuses_url) {
        this.statuses_url = statuses_url;
    }

    public void setHead(HeadEntity head) {
        this.head = head;
    }

    public void setBase(BaseEntity base) {
        this.base = base;
    }

    public void set_links(LinksEntity _links) {
        this._links = _links;
    }

    public void setMerged(boolean merged) {
        this.merged = merged;
    }

    public void setMergeable(Object mergeable) {
        this.mergeable = mergeable;
    }

    public void setMergeable_state(String mergeable_state) {
        this.mergeable_state = mergeable_state;
    }

    public void setMerged_by(Object merged_by) {
        this.merged_by = merged_by;
    }

    public void setComments(int comments) {
        this.comments = comments;
    }

    public void setReview_comments(int review_comments) {
        this.review_comments = review_comments;
    }

    public void setCommits(int commits) {
        this.commits = commits;
    }

    public void setAdditions(int additions) {
        this.additions = additions;
    }

    public void setDeletions(int deletions) {
        this.deletions = deletions;
    }

    public void setChanged_files(int changed_files) {
        this.changed_files = changed_files;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public String getHtml_url() {
        return html_url;
    }

    public String getDiff_url() {
        return diff_url;
    }

    public String getPatch_url() {
        return patch_url;
    }

    public String getIssue_url() {
        return issue_url;
    }

    public int getNumber() {
        return number;
    }

    public String getState() {
        return state;
    }

    public boolean getLocked() {
        return locked;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public Object getClosed_at() {
        return closed_at;
    }

    public Object getMerged_at() {
        return merged_at;
    }

    public Object getMerge_commit_sha() {
        return merge_commit_sha;
    }

    public Object getAssignee() {
        return assignee;
    }

    public Object getMilestone() {
        return milestone;
    }

    public String getCommits_url() {
        return commits_url;
    }

    public String getReview_comments_url() {
        return review_comments_url;
    }

    public String getReview_comment_url() {
        return review_comment_url;
    }

    public String getComments_url() {
        return comments_url;
    }

    public String getStatuses_url() {
        return statuses_url;
    }

    public HeadEntity getHead() {
        return head;
    }

    public BaseEntity getBase() {
        return base;
    }

    public LinksEntity get_links() {
        return _links;
    }

    public boolean getMerged() {
        return merged;
    }

    public Object getMergeable() {
        return mergeable;
    }

    public String getMergeable_state() {
        return mergeable_state;
    }

    public Object getMerged_by() {
        return merged_by;
    }

    public int getComments() {
        return comments;
    }

    public int getReview_comments() {
        return review_comments;
    }

    public int getCommits() {
        return commits;
    }

    public int getAdditions() {
        return additions;
    }

    public int getDeletions() {
        return deletions;
    }

    public int getChanged_files() {
        return changed_files;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class HeadEntity {
        /**
         * label : baxterthehacker:changes
         * ref : changes
         * sha : 0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
         * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
         * repo : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:26Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
         */

        private String label;
        private String ref;
        private String sha;
        private User user;
        private Repository repo;

        public void setLabel(String label) {
            this.label = label;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getLabel() {
            return label;
        }

        public String getRef() {
            return ref;
        }

        public String getSha() {
            return sha;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Repository getRepo() {
            return repo;
        }

        public void setRepo(Repository repo) {
            this.repo = repo;
        }
    }

    public static class BaseEntity {
        /**
         * label : baxterthehacker:master
         * ref : master
         * sha : 9049f1265b7d61be4a8904a9a27120d2064dab3b
         * user : {"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false}
         * repo : {"id":35129377,"name":"public-repo","full_name":"baxterthehacker/public-repo","owner":{"login":"baxterthehacker","id":6752317,"avatar_url":"https://avatars.githubusercontent.com/u/6752317?v=3","gravatar_id":"","url":"https://api.github.com/users/baxterthehacker","html_url":"https://github.com/baxterthehacker","followers_url":"https://api.github.com/users/baxterthehacker/followers","following_url":"https://api.github.com/users/baxterthehacker/following{/other_user}","gists_url":"https://api.github.com/users/baxterthehacker/gists{/gist_id}","starred_url":"https://api.github.com/users/baxterthehacker/starred{/owner}{/repo}","subscriptions_url":"https://api.github.com/users/baxterthehacker/subscriptions","organizations_url":"https://api.github.com/users/baxterthehacker/orgs","repos_url":"https://api.github.com/users/baxterthehacker/repos","events_url":"https://api.github.com/users/baxterthehacker/events{/privacy}","received_events_url":"https://api.github.com/users/baxterthehacker/received_events","type":"User","site_admin":false},"private":false,"html_url":"https://github.com/baxterthehacker/public-repo","description":"","fork":false,"url":"https://api.github.com/repos/baxterthehacker/public-repo","forks_url":"https://api.github.com/repos/baxterthehacker/public-repo/forks","keys_url":"https://api.github.com/repos/baxterthehacker/public-repo/keys{/key_id}","collaborators_url":"https://api.github.com/repos/baxterthehacker/public-repo/collaborators{/collaborator}","teams_url":"https://api.github.com/repos/baxterthehacker/public-repo/teams","hooks_url":"https://api.github.com/repos/baxterthehacker/public-repo/hooks","issue_events_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/events{/number}","events_url":"https://api.github.com/repos/baxterthehacker/public-repo/events","assignees_url":"https://api.github.com/repos/baxterthehacker/public-repo/assignees{/user}","branches_url":"https://api.github.com/repos/baxterthehacker/public-repo/branches{/branch}","tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/tags","blobs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/blobs{/sha}","git_tags_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/tags{/sha}","git_refs_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/refs{/sha}","trees_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/trees{/sha}","statuses_url":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/{sha}","languages_url":"https://api.github.com/repos/baxterthehacker/public-repo/languages","stargazers_url":"https://api.github.com/repos/baxterthehacker/public-repo/stargazers","contributors_url":"https://api.github.com/repos/baxterthehacker/public-repo/contributors","subscribers_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscribers","subscription_url":"https://api.github.com/repos/baxterthehacker/public-repo/subscription","commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/commits{/sha}","git_commits_url":"https://api.github.com/repos/baxterthehacker/public-repo/git/commits{/sha}","comments_url":"https://api.github.com/repos/baxterthehacker/public-repo/comments{/number}","issue_comment_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues/comments{/number}","contents_url":"https://api.github.com/repos/baxterthehacker/public-repo/contents/{+path}","compare_url":"https://api.github.com/repos/baxterthehacker/public-repo/compare/{base}...{head}","merges_url":"https://api.github.com/repos/baxterthehacker/public-repo/merges","archive_url":"https://api.github.com/repos/baxterthehacker/public-repo/{archive_format}{/ref}","downloads_url":"https://api.github.com/repos/baxterthehacker/public-repo/downloads","issues_url":"https://api.github.com/repos/baxterthehacker/public-repo/issues{/number}","pulls_url":"https://api.github.com/repos/baxterthehacker/public-repo/pulls{/number}","milestones_url":"https://api.github.com/repos/baxterthehacker/public-repo/milestones{/number}","notifications_url":"https://api.github.com/repos/baxterthehacker/public-repo/notifications{?since,all,participating}","labels_url":"https://api.github.com/repos/baxterthehacker/public-repo/labels{/name}","releases_url":"https://api.github.com/repos/baxterthehacker/public-repo/releases{/id}","created_at":"2015-05-05T23:40:12Z","updated_at":"2015-05-05T23:40:12Z","pushed_at":"2015-05-05T23:40:26Z","git_url":"git://github.com/baxterthehacker/public-repo.git","ssh_url":"git@github.com:baxterthehacker/public-repo.git","clone_url":"https://github.com/baxterthehacker/public-repo.git","svn_url":"https://github.com/baxterthehacker/public-repo","homepage":null,"size":0,"stargazers_count":0,"watchers_count":0,"language":null,"has_issues":true,"has_downloads":true,"has_wiki":true,"has_pages":true,"forks_count":0,"mirror_url":null,"open_issues_count":1,"forks":0,"open_issues":1,"watchers":0,"default_branch":"master"}
         */

        private String label;
        private String ref;
        private String sha;
        private User user;
        private Repository repo;

        public void setLabel(String label) {
            this.label = label;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }

        public void setSha(String sha) {
            this.sha = sha;
        }

        public String getLabel() {
            return label;
        }

        public String getRef() {
            return ref;
        }

        public String getSha() {
            return sha;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Repository getRepo() {
            return repo;
        }

        public void setRepo(Repository repo) {
            this.repo = repo;
        }
    }

    public static class LinksEntity {
        /**
         * self : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1"}
         * html : {"href":"https://github.com/baxterthehacker/public-repo/pull/1"}
         * issue : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1"}
         * comments : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments"}
         * review_comments : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments"}
         * review_comment : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}"}
         * commits : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits"}
         * statuses : {"href":"https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c"}
         */

        private SelfEntity self;
        private HtmlEntity html;
        private IssueEntity issue;
        private CommentsEntity comments;
        private ReviewCommentsEntity review_comments;
        private ReviewCommentEntity review_comment;
        private CommitsEntity commits;
        private StatusesEntity statuses;

        public void setSelf(SelfEntity self) {
            this.self = self;
        }

        public void setHtml(HtmlEntity html) {
            this.html = html;
        }

        public void setIssue(IssueEntity issue) {
            this.issue = issue;
        }

        public void setComments(CommentsEntity comments) {
            this.comments = comments;
        }

        public void setReview_comments(ReviewCommentsEntity review_comments) {
            this.review_comments = review_comments;
        }

        public void setReview_comment(ReviewCommentEntity review_comment) {
            this.review_comment = review_comment;
        }

        public void setCommits(CommitsEntity commits) {
            this.commits = commits;
        }

        public void setStatuses(StatusesEntity statuses) {
            this.statuses = statuses;
        }

        public SelfEntity getSelf() {
            return self;
        }

        public HtmlEntity getHtml() {
            return html;
        }

        public IssueEntity getIssue() {
            return issue;
        }

        public CommentsEntity getComments() {
            return comments;
        }

        public ReviewCommentsEntity getReview_comments() {
            return review_comments;
        }

        public ReviewCommentEntity getReview_comment() {
            return review_comment;
        }

        public CommitsEntity getCommits() {
            return commits;
        }

        public StatusesEntity getStatuses() {
            return statuses;
        }

        public static class SelfEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class HtmlEntity {
            /**
             * href : https://github.com/baxterthehacker/public-repo/pull/1
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class IssueEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/issues/1
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class CommentsEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/issues/1/comments
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class ReviewCommentsEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/comments
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class ReviewCommentEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/comments{/number}
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class CommitsEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/pulls/1/commits
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }

        public static class StatusesEntity {
            /**
             * href : https://api.github.com/repos/baxterthehacker/public-repo/statuses/0d1a26e67d8f5eaf1f6ba5c57fc3c7d91ac0fd1c
             */

            private String href;

            public void setHref(String href) {
                this.href = href;
            }

            public String getHref() {
                return href;
            }
        }
    }
}
