"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.decorateNoRedeclare = void 0;
const utils_1 = require("../utils");
function decorateNoRedeclare(rule) {
    return (0, utils_1.interceptReport)(rule, reportExempting(isTypeDeclaration));
}
exports.decorateNoRedeclare = decorateNoRedeclare;
function reportExempting(exemptionCondition) {
    return (context, reportDescriptor) => {
        if ('node' in reportDescriptor) {
            const node = reportDescriptor['node'];
            if (node.type === 'Identifier' && !exemptionCondition(node)) {
                context.report(reportDescriptor);
            }
        }
    };
}
function isTypeDeclaration(node) {
    var _a;
    return ((_a = node.parent) === null || _a === void 0 ? void 0 : _a.type) === 'TSTypeAliasDeclaration';
}
//# sourceMappingURL=no-redeclare-decorator.js.map