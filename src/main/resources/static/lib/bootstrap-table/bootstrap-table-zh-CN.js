(function (global, factory) {
  if (typeof define === "function" && define.amd) {
    define([], factory);
  } else if (typeof exports !== "undefined") {
    factory();
  } else {
    var mod = {
      exports: {}
    };
    factory();
    global.bootstrapTableZhCN = mod.exports;
  }
})(this, function () {
  'use strict';

  /**
   * Bootstrap Table Chinese translation
   * Author: Zhixin Wen<wenzhixin2010@gmail.com>
   */
  (function ($) {
    $.fn.bootstrapTable.locales['zh-CN'] = {
      formatLoadingMessage: function formatLoadingMessage() {
        return '����Ŭ���ؼ��������У����Ժ򡭡�';
      },
      formatRecordsPerPage: function formatRecordsPerPage(pageNumber) {
        return '\u6BCF\u9875\u663E\u793A ' + pageNumber + ' \u6761\u8BB0\u5F55';
      },
      formatShowingRows: function formatShowingRows(pageFrom, pageTo, totalRows) {
        return '\u663E\u793A\u7B2C ' + pageFrom + ' \u5230\u7B2C ' + pageTo + ' \u6761\u8BB0\u5F55\uFF0C\u603B\u5171 ' + totalRows + ' \u6761\u8BB0\u5F55';
      },
      formatSearch: function formatSearch() {
        return '����';
      },
      formatNoMatches: function formatNoMatches() {
        return 'û���ҵ�ƥ��ļ�¼';
      },
      formatPaginationSwitch: function formatPaginationSwitch() {
        return '����/��ʾ��ҳ';
      },
      formatRefresh: function formatRefresh() {
        return 'ˢ��';
      },
      formatToggle: function formatToggle() {
        return '�л�';
      },
      formatColumns: function formatColumns() {
        return '��';
      },
      formatExport: function formatExport() {
        return '��������';
      },
      formatClearFilters: function formatClearFilters() {
        return '��չ���';
      }
    };

    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN']);
  })(jQuery);
});