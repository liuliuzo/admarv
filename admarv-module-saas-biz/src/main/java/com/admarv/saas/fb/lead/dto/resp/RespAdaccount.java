package com.admarv.saas.fb.lead.dto.resp;

/**
 * 广告庄户查询响应
 * 
 * @author liuliu
 *
 */
public class RespAdaccount {
    
    private String id;
    private long amountSpent;
    private long balance;
    private String currency;
    private String accountStatus;
    private String accountId;
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public long getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(long amountSpent) {
        this.amountSpent = amountSpent;
    }

    public long getBalance() {
        return balance;
    }

    public void setBalance(long balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(String accountStatus) {
        this.accountStatus = accountStatus;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "RespAdaccount [id=" + id + ", amountSpent=" + amountSpent + ", balance=" + balance + ", currency="
                + currency + ", accountStatus=" + accountStatus + ", accountId=" + accountId + "]";
    }
//    id：广告账户的唯一标识符。
//    account_id：广告账户的 ID。
//    account_status：广告账户的状态。
//    ad_account_promotable_objects：广告账户可推广的对象。
//    age：广告账户的年龄。
//    agency_client_declaration：代理商客户声明。
//    amount_spent：广告账户已花费的金额。
//    attribution_spec：归因规范。
//    balance：广告账户的余额。
//    business：广告账户所属的业务实体。
//    business_city：业务实体所在的城市。
//    business_country_code：业务实体所在的国家代码。
//    business_name：业务实体的名称。
//    business_state：业务实体所在的州或省份。
//    business_street：业务实体所在的街道地址。
//    business_zip：业务实体所在的邮政编码。
//    can_create_brand_lift_study：是否可以创建品牌提升研究。
//    capabilities：广告账户的功能。
//    created_time：广告账户的创建时间。
//    currency：广告账户使用的货币。
//    direct_deals_tos_accepted：是否接受直接交易条款。
//    disable_reason：禁用广告账户的原因。
//    end_advertiser：最终广告主。
//    end_advertiser_name：最终广告主名称。
//    existing_customers：现有客户。
//    extended_credit_invoice_group：扩展信用发票组。
//    failed_delivery_checks：投放检查失败次数。
//    fb_entity：Facebook 实体。
//    funding_source：资金来源。
//    funding_source_details：资金来源的详细信息。
//    has_migrated_permissions：是否已迁移权限。
//    has_page_authorized_adaccount：页面是否授权广告账户。
//    io_number：IO 编号。
//    is_attribution_spec_system_default：是否为归因规范系统默认设置。
//    is_notifications_enabled：是否启用通知。
//    media_agency：媒体代理商。
//    name：广告账户名称。
//    partner：合作伙伴。
//    spend_cap：广告账户的花费上限。
}